package edu.mangement.service.Impl;

import edu.mangement.entity.Order;
import edu.mangement.entity.sp.DayQuantityMapper;
import edu.mangement.mapper.OrderMapper;
import edu.mangement.model.OrderDTO;
import edu.mangement.model.OrderFilterForm;
import edu.mangement.model.Paging;
import edu.mangement.service.OrderService;
import edu.mangement.utils.DateFormatUtils3;
import edu.mangement.utils.MapAPIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/28/2020
 * TIME : 8:54 PM
 */
@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<OrderDTO> search(OrderFilterForm orderFilterForm, Paging paging) {
        var queryStr = new StringBuilder("SELECT o from Order o WHERE o.activeFlag =:activeFlag ");
        var countQueryStr = new StringBuilder("SELECT count(*) from Order o WHERE o.activeFlag =:activeFlag");
        Map<String, Object> mapParams = new HashMap<>();
        prepareQuery(orderFilterForm, queryStr, countQueryStr, mapParams);
        TypedQuery<Order> query = entityManager.createQuery(queryStr.toString(), Order.class);
        Query countQuery = entityManager.createQuery(countQueryStr.toString());
        prepareStatement(mapParams, query, countQuery);
        pagingProcessor(paging, query, countQuery);
        return query.getResultList().stream().map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> countOrderByDay(Date date) {
        var mapDay = MapAPIUtils.getMapDay();
        List<DayQuantityMapper> resultList = entityManager.createNamedStoredProcedureQuery("Order.countOrderByDay")
                .setParameter("DATE_FROM", date).getResultList();
        resultList.forEach(dayQuantityMapper -> {
            mapDay.put(dayQuantityMapper.getDay(),dayQuantityMapper.getQuantity());
        });
        return mapDay;
    }

    private void prepareStatement(Map<String, Object> mapParams, TypedQuery<Order> query, Query countQuery) {
        //set parameter
        if (mapParams != null && !mapParams.isEmpty()) {
            mapParams.forEach((k, v) -> {
                query.setParameter(k, v);
                countQuery.setParameter(k, v);
            });
        }
    }

    private void pagingProcessor(Paging paging, TypedQuery<Order> query, Query countQuery) {
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
                appendStr.append(" and o.customer.name =:customerName ");
                mapParams.put("customerName", orderFilterForm.getName());
            }
            //check upto > 0
            if (orderFilterForm.getUpTo()!=null&&orderFilterForm.getUpTo().compareTo(BigDecimal.valueOf(0)) == 1) {
                appendStr.append(" and o.totalOrder <= :upTo ");
                mapParams.put("upTo", orderFilterForm.getUpTo());
            }
            //check from date and to date
            if (orderFilterForm.getFromDate() != null) {
                appendStr.append(" and o.updateDate >= :fromDate");
                mapParams.put("fromDate", orderFilterForm.getFromDate());
            }
            if (orderFilterForm.getToDate() != null) {
                appendStr.append(" and o.updateDate <= :toDate");
                mapParams.put("toDate", orderFilterForm.getToDate());
            }
        }
//        appendStr.append(" ORDER BY o.updateDate DESC ");
        queryStr.append(appendStr);
        countQueryStr.append(appendStr);
    }
}
