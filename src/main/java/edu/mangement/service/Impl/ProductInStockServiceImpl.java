package edu.mangement.service.Impl;

import edu.mangement.entity.ProductInStock;
import edu.mangement.mapper.ProductInStockMapper;
import edu.mangement.model.Paging;
import edu.mangement.model.ProductInStockDTO;
import edu.mangement.repository.ProductInStockRepository;
import edu.mangement.service.ProductInStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
 * TIME : 3:47 PM
 */
@Component
public class ProductInStockServiceImpl implements ProductInStockService {
    @Autowired
    private ProductInStockRepository productInStockRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ProductInStockDTO> findAll(Pageable pageable, Paging paging) {
        var pagePIS = productInStockRepository.findAllByActiveFlag(1, pageable);
        paging.setTotalPages(pagePIS.getTotalPages());
        paging.setTotalRows(pagePIS.getTotalElements());
        return pagePIS.get().map(ProductInStockMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProductInStockDTO findById(Long id) {
        return Optional.ofNullable(id)
                .map(pisId -> productInStockRepository
                        .findProductInStockByIdAndActiveFlag(pisId, 1))
                .map(ProductInStockMapper::toDTO)
                .orElse(null);
    }

    @Override
    public ProductInStockDTO findByCode(String code) {
        return Optional.ofNullable(code)
                .map(s -> productInStockRepository
                        .findProductInStockByProduct_CodeAndActiveFlag(s, 1))
                .map(ProductInStockMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<ProductInStockDTO> search(ProductInStockDTO productInStockDTO, Paging paging) {
        StringBuilder queryStr = new StringBuilder("SELECT pis from ProductInStock pis WHERE pis.activeFlag =:activeFlag ");
        StringBuilder countQueryStr = new StringBuilder("SELECT count(*) from ProductInStock pis WHERE pis.activeFlag =:activeFlag");
        Map<String, Object> mapParams = new HashMap<>();
        prepareQuery(productInStockDTO, queryStr, countQueryStr, mapParams);
        TypedQuery<ProductInStock> query = entityManager.createQuery(queryStr.toString(), ProductInStock.class);
        Query countQuery = entityManager.createQuery(countQueryStr.toString());
        //set parameter
        if (mapParams != null && !mapParams.isEmpty()) {
            mapParams.forEach((k, v) -> {
                query.setParameter(k, v);
                countQuery.setParameter(k,v);
            });
        }
        query.setParameter("activeFlag",1);
        countQuery.setParameter("activeFlag",1);
        if(paging!=null){
            query.setFirstResult(paging.getOffset());
            query.setMaxResults(paging.getRecordPerPage());
            paging.setTotalRows((Long) countQuery.getSingleResult());
            paging.setTotalPages((int) Math.ceil(paging.getTotalRows() / (double) paging.getRecordPerPage()));
        }
        return query.getResultList().stream().map(ProductInStockMapper::toDTO).collect(Collectors.toList());
    }

    private void prepareQuery(ProductInStockDTO productInStockDTO, StringBuilder queryStr, StringBuilder countQueryStr, Map<String, Object> mapParams) {
        StringBuilder appendStr = new StringBuilder();
        //map params for search
        if (productInStockDTO != null) {
            if (productInStockDTO.getProduct() != null) {
                if (!productInStockDTO.getProduct().getCode().isBlank()) {
                    appendStr.append(" and pis.product.code =:productCode ");
                    mapParams.put("productCode", productInStockDTO.getProduct().getCode());
                }
                if (productInStockDTO.getProduct().getCategory() != null) {
                    if (productInStockDTO.getProduct().getCategory().getId()!=null) {
                        appendStr.append(" and pis.product.category.id =:categoryId ");
                        mapParams.put("categoryId", productInStockDTO.getProduct().getCategory().getId());
                    }
                }
            }
            if (productInStockDTO.getBranch() != null) {
                if (productInStockDTO.getBranch().getId() != null) {
                    appendStr.append(" and pis.branch.id =:branchId ");
                    mapParams.put("branchId", productInStockDTO.getBranch().getId());
                }
            }
            if (productInStockDTO.getSize()!=null) {
                if (!productInStockDTO.getSize().isBlank()) {
                mapParams.put("size", productInStockDTO.getSize());
                appendStr.append(" and pis.size =:size");
                }
            }
        }
        queryStr.append(appendStr);
        countQueryStr.append(appendStr);
    }
}
