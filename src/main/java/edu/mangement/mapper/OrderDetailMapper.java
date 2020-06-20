package edu.mangement.mapper;

import edu.mangement.entity.OrderDetail;
import edu.mangement.model.OrderDetailDTO;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 6:02 PM
 */
public class OrderDetailMapper {
    public static OrderDetailDTO toDTO(OrderDetail orderDetailEntity) {
        OrderDetailDTO orderDetailDTO = OrderDetailDTO.builder()
                .id(orderDetailEntity.getId())
                .unitPrice(orderDetailEntity.getUnitPrice())
                .quantity(orderDetailEntity.getQuantity())
                .createDate(orderDetailEntity.getCreateDate())
                .updateDate(orderDetailEntity.getUpdateDate())
                .activeFlag(orderDetailEntity.getActiveFlag())
                .build();
        if (orderDetailEntity.getItems() != null) {
            orderDetailDTO.setItems(ItemsMapper.toDTO(orderDetailEntity.getItems()));
        }
        return orderDetailDTO;
    }

    public static OrderDetail toEntity(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetailEntity = OrderDetail.builder()
                .id(orderDetailDTO.getId())
                .unitPrice(orderDetailDTO.getUnitPrice())
                .quantity(orderDetailDTO.getQuantity())
                .createDate(orderDetailDTO.getCreateDate())
                .updateDate(orderDetailDTO.getUpdateDate())
                .activeFlag(orderDetailDTO.getActiveFlag())
                .build();
        if (orderDetailDTO.getItems() != null) {
            orderDetailEntity.setItems(ItemsMapper.toEntity(orderDetailDTO.getItems()));
        }
        return orderDetailEntity;
    }
}
