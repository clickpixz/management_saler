package edu.mangement.mapper;

import edu.mangement.entity.Order;
import edu.mangement.model.dto.OrderDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 6:01 PM
 */
public class OrderMapper {
    public static OrderDTO toDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .totalOrder(order.getTotalOrder())
                .deliveryAddress(order.getDeliveryAddress())
                .description(order.getDescription())
                .statusDelivery(order.getStatusDelivery())
                .createDate(order.getCreateDate())
                .updateDate(order.getUpdateDate())
                .activeFlag(order.getActiveFlag())
                .customer(CustomerMapper.toDTO(order.getCustomer()))
                .orderDetails(order.getOrderDetails().stream().map(OrderDetailMapper::toDTO).collect(Collectors.toList()))
                .build();
    }

    public static Order toEntity(OrderDTO order) {
        return Order.builder()
                .id(order.getId())
                .totalOrder(order.getTotalOrder())
                .deliveryAddress(order.getDeliveryAddress())
                .description(order.getDescription())
                .statusDelivery(order.getStatusDelivery())
                .createDate(order.getCreateDate())
                .updateDate(order.getUpdateDate())
                .activeFlag(order.getActiveFlag())
                .customer(CustomerMapper.toEntity(order.getCustomer()))
                .orderDetails(order.getOrderDetails().stream().map(OrderDetailMapper::toEntity).collect(Collectors.toList()))
                .build();
    }
}
