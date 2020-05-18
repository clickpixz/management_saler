package edu.mangement.mapper.model;

import edu.mangement.entity.Items;
import edu.mangement.model.dto.ItemsDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:55 PM
 */
public class ItemsMapper {
    public static ItemsDTO toDTO(Items items) {
        return ItemsDTO.builder()
                .id(items.getId())
                .price(items.getPrice())
                .createDate(items.getCreateDate())
                .updateDate(items.getUpdateDate())
                .activeFlag(items.getActiveFlag())
                .productInStock(ProductInStockMapper.toDTO(items.getProductInStock()))
                .invoiceDetails(items.getInvoiceDetails().stream().map(InvoiceDetailMapper::toDTO).collect(Collectors.toList()))
                .orderDetails(items.getOrderDetails().stream().map(OrderDetailMapper::toDTO).collect(Collectors.toList()))
                .build();
    }
}
