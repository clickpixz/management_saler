package edu.mangement.service.Impl;

import edu.mangement.entity.sp.TopProductSell;
import edu.mangement.model.Paging;
import edu.mangement.service.InvoiceDetailService;
import edu.mangement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 6/6/2020
 * TIME : 9:53 PM
 */
@Component
@Transactional(rollbackOn = Exception.class)
public class InvoiceDetailServiceImpl implements InvoiceDetailService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductService productService;
    @Override
    public List<TopProductSell> getTopProductSell(Date date, Paging paging) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("InvoiceDetail.getTopSeller")
                .setParameter("DATE_FROM", date);
        if(paging!=null){
            Long totalRows = productService.getTotalQuantityProductSell();
            storedProcedureQuery.setFirstResult(paging.getOffset());
            storedProcedureQuery.setMaxResults(paging.getRecordPerPage());
            paging.setTotalRows(totalRows);
            paging.setTotalPages((int) Math.ceil(paging.getTotalRows() / (double) paging.getRecordPerPage()));
        }
        List<TopProductSell> resultList = storedProcedureQuery.getResultList();
        return resultList.stream().map(topProductSell -> {
            topProductSell.setProductDTO(productService.findProductById(topProductSell.getProductDTO().getId()));
            return topProductSell;
        }).collect(Collectors.toList());
    }
}
