package edu.mangement.service.Impl;

import edu.mangement.entity.Invoice;
import edu.mangement.mapper.InvoiceMapper;
import edu.mangement.model.InvoiceDTO;
import edu.mangement.model.OrderFilterForm;
import edu.mangement.model.Paging;
import edu.mangement.repository.InvoiceRepository;
import edu.mangement.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/29/2020
 * TIME : 8:58 PM
 */
@Component
@Transactional(rollbackOn = Exception.class)
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceDTO> search(OrderFilterForm orderFilterForm, Paging paging) {
        var queryStr = new StringBuilder("SELECT model from Invoice model WHERE model.activeFlag =:activeFlag ");
        var countQueryStr = new StringBuilder("SELECT count(*) from Invoice model WHERE model.activeFlag =:activeFlag");
        Map<String, Object> mapParams = new HashMap<>();
        prepareQuery(orderFilterForm, queryStr, countQueryStr, mapParams);
        TypedQuery<Invoice> query = entityManager.createQuery(queryStr.toString(), Invoice.class);
        Query countQuery = entityManager.createQuery(countQueryStr.toString());
        prepareStatement(mapParams, query, countQuery);
        pagingProcessor(paging, query, countQuery);
        return query.getResultList().stream().map(InvoiceMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO getById(Long id) {
        return Optional.ofNullable(id)
                .map(invoiceId -> invoiceRepository.getInvoiceByIdAndActiveFlag(invoiceId,1))
                .map(InvoiceMapper::toDTO)
                .orElse(null);
    }

    private void prepareStatement(Map<String, Object> mapParams, TypedQuery<Invoice> query, Query countQuery) {
        //set parameter
        if (mapParams != null && !mapParams.isEmpty()) {
            mapParams.forEach((k, v) -> {
                query.setParameter(k, v);
                countQuery.setParameter(k, v);
            });
        }
    }

    private void pagingProcessor(Paging paging, TypedQuery<Invoice> query, Query countQuery) {
        query.setParameter("activeFlag", 1);
        countQuery.setParameter("activeFlag", 1);
        if (paging != null) {
            query.setFirstResult(paging.getOffset());
            query.setMaxResults(paging.getRecordPerPage());
            paging.setTotalRows((Long) countQuery.getSingleResult());
            paging.setTotalPages((int) Math.ceil(paging.getTotalRows() / (double) paging.getRecordPerPage()));
        }
    }

    private void prepareQuery(OrderFilterForm orderFilterForm, StringBuilder queryStr, StringBuilder countQueryStr, Map<String, Object> mapParams) {
        var appendStr = new StringBuilder();
        //map params for search
        if (orderFilterForm != null) {
            if (orderFilterForm.getName() != null && !orderFilterForm.getName().isBlank()) {
                appendStr.append(" and model.nameCustomer =:nameCustomer ");
                mapParams.put("nameCustomer", orderFilterForm.getName());
            }
            //check upto > 0
            if (orderFilterForm.getUpTo() != null && orderFilterForm.getUpTo().compareTo(BigDecimal.valueOf(0)) > 0) {
                appendStr.append(" and model.totalOrder <= :upTo ");
                mapParams.put("upTo", orderFilterForm.getUpTo());
            }
            //check from date and to date
            if (orderFilterForm.getFromDate() != null) {
                appendStr.append(" and model.updateDate >= :fromDate");
                mapParams.put("fromDate", orderFilterForm.getFromDate());
            }
            if (orderFilterForm.getToDate() != null) {
                appendStr.append(" and model.updateDate <= :toDate");
                mapParams.put("toDate", orderFilterForm.getToDate());
            }
        }
//        appendStr.append(" ORDER BY o.updateDate DESC ");
        queryStr.append(appendStr);
        countQueryStr.append(appendStr);
    }
}
