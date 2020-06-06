package edu.mangement.service.Impl;

import edu.mangement.entity.sp.TopProductSell;
import edu.mangement.mapper.OrderDetailMapper;
import edu.mangement.model.OrderDetailDTO;
import edu.mangement.model.Paging;
import edu.mangement.repository.OrderDetailRepository;
import edu.mangement.service.OrderDetailService;
import edu.mangement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/29/2020
 * TIME : 10:53 PM
 */
@Component
@Transactional(rollbackOn = Exception.class)
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductService productService;
    @Override
    public List<OrderDetailDTO> findOrderDetailByOrderId(Long orderId, Paging paging, Pageable pageable) {
        var orderDetails = Optional.ofNullable(orderId)
                .map(id -> orderDetailRepository
                        .findOrderDetailByActiveFlagAndOrder_Id(1, orderId, pageable))
                .orElse(null);
        paging.setTotalPages(orderDetails.getTotalPages());
        paging.setTotalRows(orderDetails.getTotalElements());
        return orderDetails.get().map(OrderDetailMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TopProductSell> getTopProductSell(Date date, Paging paging) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("OrderDetail.TopProductSell")
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
