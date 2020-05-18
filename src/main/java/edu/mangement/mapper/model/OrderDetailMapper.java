package edu.mangement.mapper.model;

import edu.mangement.entity.OrderDetail;
import edu.mangement.model.dto.OrderDetailDTO;
import org.springframework.stereotype.Component;

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
}
