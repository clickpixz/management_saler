package edu.mangement.mapper;

import edu.mangement.entity.OrderDetail;
import edu.mangement.model.dto.OrderDetailDTO;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 6:02 PM
 */
public class OrderDetailMapper {
    public static OrderDetailDTO toDTO(OrderDetail orderDetail) {
        return OrderDetailDTO.builder()
                .id(orderDetail.getId())
                .unitPrice(orderDetail.getUnitPrice())
                .quantity(orderDetail.getQuantity())
                .createDate(orderDetail.getCreateDate())
                .updateDate(orderDetail.getUpdateDate())
                .activeFlag(orderDetail.getActiveFlag())
                .items(ItemsMapper.toDTO(orderDetail.getItems()))
                .order(OrderMapper.toDTO(orderDetail.getOrder()))
                .build();
    }

    public static OrderDetail toEntity(OrderDetailDTO orderDetail) {
        return OrderDetail.builder()
                .id(orderDetail.getId())
                .unitPrice(orderDetail.getUnitPrice())
                .quantity(orderDetail.getQuantity())
                .createDate(orderDetail.getCreateDate())
                .updateDate(orderDetail.getUpdateDate())
                .activeFlag(orderDetail.getActiveFlag())
                .items(ItemsMapper.toEntity(orderDetail.getItems()))
                .order(OrderMapper.toEntity(orderDetail.getOrder()))
                .build();
    }
}
