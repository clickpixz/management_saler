package edu.mangement.mapper;

import edu.mangement.entity.Order;
import edu.mangement.model.OrderDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 6:01 PM
 */
public class OrderMapper {
    public static OrderDTO toDTO(Order orderEntity) {
        OrderDTO orderDTO = OrderDTO.builder()
                .id(orderEntity.getId())
                .totalOrder(orderEntity.getTotalOrder())
                .deliveryAddress(orderEntity.getDeliveryAddress())
                .description(orderEntity.getDescription())
                .statusDelivery(orderEntity.getStatusDelivery())
                .createDate(orderEntity.getCreateDate())
                .updateDate(orderEntity.getUpdateDate())
                .activeFlag(orderEntity.getActiveFlag())
                .build();
        if (orderEntity.getCustomer() != null) {
            orderDTO.setCustomer(CustomerMapper.toDTO(orderEntity.getCustomer()));
        }
        if (orderEntity.getOrderDetails() != null) {
            orderDTO.setOrderDetails(orderEntity.getOrderDetails().stream().map(OrderDetailMapper::toDTO).collect(Collectors.toList()));
        }
        return orderDTO;
    }

    public static Order toEntity(OrderDTO orderDTO) {
        Order orderEntity = Order.builder()
                .id(orderDTO.getId())
                .totalOrder(orderDTO.getTotalOrder())
                .deliveryAddress(orderDTO.getDeliveryAddress())
                .description(orderDTO.getDescription())
                .statusDelivery(orderDTO.getStatusDelivery())
                .createDate(orderDTO.getCreateDate())
                .updateDate(orderDTO.getUpdateDate())
                .activeFlag(orderDTO.getActiveFlag())
                .build();
        if (orderDTO.getCustomer() != null) {
            orderEntity.setCustomer(CustomerMapper.toEntity(orderDTO.getCustomer()));
        }
        if (orderDTO.getOrderDetails() != null) {
            orderEntity.setOrderDetails(orderDTO.getOrderDetails().stream().map(OrderDetailMapper::toEntity).collect(Collectors.toList()));
        }
        return orderEntity;
    }
}
