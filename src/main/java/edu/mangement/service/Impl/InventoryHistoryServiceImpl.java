package edu.mangement.service.Impl;

import edu.mangement.entity.InventoryHistory;
import edu.mangement.mapper.InventoryHistoryMapper;
import edu.mangement.model.InventoryHistoryDTO;
import edu.mangement.model.Paging;
import edu.mangement.service.InventoryHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/27/2020
 * TIME : 11:49 PM
 */
@Component
public class InventoryHistoryServiceImpl implements InventoryHistoryService {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<InventoryHistoryDTO> search(InventoryHistoryDTO inventoryHistoryDTO, Paging paging) {
        var queryStr = new StringBuilder("SELECT ih from InventoryHistory ih WHERE ih.activeFlag =:activeFlag ");
        var countQueryStr = new StringBuilder("SELECT count(*) from InventoryHistory ih WHERE ih.activeFlag =:activeFlag");
        Map<String, Object> mapParams = new HashMap<>();
        prepareQuery(inventoryHistoryDTO, queryStr, countQueryStr, mapParams);
        TypedQuery<InventoryHistory> query = entityManager.createQuery(queryStr.toString(), InventoryHistory.class);
        Query countQuery = entityManager.createQuery(countQueryStr.toString());
        prepareStatement(mapParams, query, countQuery);
        pagingProcessor(paging, query, countQuery);
        return query.getResultList().stream().map(InventoryHistoryMapper::toDTO).collect(Collectors.toList());
    }
    private void prepareStatement(Map<String, Object> mapParams, TypedQuery<InventoryHistory> query, Query countQuery) {
        //set parameter
        if (mapParams != null && !mapParams.isEmpty()) {
            mapParams.forEach((k, v) -> {
                query.setParameter(k, v);
                countQuery.setParameter(k, v);
            });
        }
    }

    private void pagingProcessor(Paging paging, TypedQuery<InventoryHistory> query, Query countQuery) {
        query.setParameter("activeFlag", 1);
        countQuery.setParameter("activeFlag", 1);
        if (paging != null) {
            query.setFirstResult(paging.getOffset());
            query.setMaxResults(paging.getRecordPerPage());
            paging.setTotalRows((Long) countQuery.getSingleResult());
            paging.setTotalPages((int) Math.ceil(paging.getTotalRows() / (double) paging.getRecordPerPage()));
        }
    }

    private void prepareQuery(InventoryHistoryDTO inventoryHistoryDTO, StringBuilder queryStr, StringBuilder countQueryStr, Map<String, Object> mapParams) {
        var appendStr = new StringBuilder();
        //map params for search
        if (inventoryHistoryDTO != null) {
            if (inventoryHistoryDTO.getProductInStock() != null) {
                if (inventoryHistoryDTO.getProductInStock().getProduct() != null) {

                    if (!inventoryHistoryDTO.getProductInStock().getProduct().getCode().isBlank()) {
                        appendStr.append(" and ih.productInStock.product.code =:productCode ");
                        mapParams.put("productCode", inventoryHistoryDTO.getProductInStock().getProduct().getCode());
                    }
                }
                if (inventoryHistoryDTO.getProductInStock().getBranch() != null) {
                    if (inventoryHistoryDTO.getProductInStock().getBranch().getId() != null) {
                        appendStr.append(" and ih.productInStock.branch.id =:branchId ");
                        mapParams.put("branchId", inventoryHistoryDTO.getProductInStock().getBranch().getId());
                    }
                }
            }
            if(inventoryHistoryDTO.getCode()!=null&& !inventoryHistoryDTO.getCode().isBlank()){
                appendStr.append(" and ih.code =:inventoryHistoryCode ");
                mapParams.put("inventoryHistoryCode",inventoryHistoryDTO.getCode());
            }
            if (inventoryHistoryDTO.getType()!=null){
                appendStr.append(" and ih.type =:type ");
                mapParams.put("type",inventoryHistoryDTO.getType());
            }
        }
        queryStr.append(appendStr);
        countQueryStr.append(appendStr);
    }
}
