package edu.mangement.service.Impl;

import edu.mangement.mapper.OrderDetailMapper;
import edu.mangement.model.OrderDetailDTO;
import edu.mangement.model.Paging;
import edu.mangement.repository.OrderDetailRepository;
import edu.mangement.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

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
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

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
}
