package edu.mangement.service.Impl;

import edu.mangement.entity.Items;
import edu.mangement.mapper.ItemsMapper;
import edu.mangement.model.ItemsDTO;
import edu.mangement.model.Paging;
import edu.mangement.repository.ItemsRepository;
import edu.mangement.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/27/2020
 * TIME : 10:24 PM
 */
@Component
public class ItemsServiceImpl implements ItemsService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ItemsRepository itemsRepository;

    @Override
    public List<ItemsDTO> search(ItemsDTO itemsDTO, Paging paging) {
        var queryStr = new StringBuilder("SELECT i from Items i WHERE i.activeFlag =:activeFlag ");
        var countQueryStr = new StringBuilder("SELECT count(*) from Items i WHERE i.activeFlag =:activeFlag");
        Map<String, Object> mapParams = new HashMap<>();
        prepareQuery(itemsDTO, queryStr, countQueryStr, mapParams);
        TypedQuery<Items> query = entityManager.createQuery(queryStr.toString(), Items.class);
        Query countQuery = entityManager.createQuery(countQueryStr.toString());
        prepareStatement(mapParams, query, countQuery);
        pagingProcessor(paging, query, countQuery);
        return query.getResultList().stream().map(ItemsMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ItemsDTO findById(Long id) {
        return Optional.ofNullable(id)
                .map(itemsId->itemsRepository.getOne(itemsId))
                .map(ItemsMapper::toDTO)
                .orElse(null);
    }

    @Override
    public void saveItems(ItemsDTO itemsDTO) throws Exception{
        itemsRepository.save(ItemsMapper.toEntity(itemsDTO));
    }

    private void prepareStatement(Map<String, Object> mapParams, TypedQuery<Items> query, Query countQuery) {
        //set parameter
        if (mapParams != null && !mapParams.isEmpty()) {
            mapParams.forEach((k, v) -> {
                query.setParameter(k, v);
                countQuery.setParameter(k, v);
            });
        }
    }

    private void pagingProcessor(Paging paging, TypedQuery<Items> query, Query countQuery) {
        query.setParameter("activeFlag", 1);
        countQuery.setParameter("activeFlag", 1);
        if (paging != null) {
            query.setFirstResult(paging.getOffset());
            query.setMaxResults(paging.getRecordPerPage());
            paging.setTotalRows((Long) countQuery.getSingleResult());
            paging.setTotalPages((int) Math.ceil(paging.getTotalRows() / (double) paging.getRecordPerPage()));
        }
    }

    private void prepareQuery(ItemsDTO itemsDTO, StringBuilder queryStr, StringBuilder countQueryStr, Map<String, Object> mapParams) {
        var appendStr = new StringBuilder();
        //map params for search
        if (itemsDTO != null) {
            if (itemsDTO.getProductInStock() != null) {
                if (itemsDTO.getProductInStock().getProduct() != null) {

                    if (!itemsDTO.getProductInStock().getProduct().getCode().isBlank()) {
                        appendStr.append(" and i.productInStock.product.code =:productCode ");
                        mapParams.put("productCode", itemsDTO.getProductInStock().getProduct().getCode());
                    }

                    if (itemsDTO.getProductInStock().getProduct().getCategory() != null) {
                        if (itemsDTO.getProductInStock().getProduct().getCategory().getId() != null) {
                            appendStr.append(" and i.productInStock.product.category.id =:categoryId ");
                            mapParams.put("categoryId", itemsDTO.getProductInStock().getProduct().getCategory().getId());
                        }
                    }

                }
                if (itemsDTO.getProductInStock().getBranch() != null) {
                    if (itemsDTO.getProductInStock().getBranch().getId() != null) {
                        appendStr.append(" and i.productInStock.branch.id =:branchId ");
                        mapParams.put("branchId", itemsDTO.getProductInStock().getBranch().getId());
                    }
                }

                if (itemsDTO.getProductInStock().getSize() != null) {
                    if (!itemsDTO.getProductInStock().getSize().isBlank()) {
                        mapParams.put("size", itemsDTO.getProductInStock().getSize());
                        appendStr.append(" and i.productInStock.size =:size");
                    }
                }
            }
        }
        queryStr.append(appendStr);
        countQueryStr.append(appendStr);
    }
}
