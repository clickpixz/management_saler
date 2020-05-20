package edu.mangement.mapper;

import edu.mangement.entity.Items;
import edu.mangement.model.ItemsDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:55 PM
 */
public class ItemsMapper {
    public static ItemsDTO toDTO(Items itemsEntity) {
        ItemsDTO itemsDTO = ItemsDTO.builder()
                .id(itemsEntity.getId())
                .price(itemsEntity.getPrice())
                .createDate(itemsEntity.getCreateDate())
                .updateDate(itemsEntity.getUpdateDate())
                .activeFlag(itemsEntity.getActiveFlag())
                .build();
        if (itemsEntity.getProductInStock() != null) {
            itemsDTO.setProductInStock(ProductInStockMapper.toDTO(itemsEntity.getProductInStock()));
        }
//        if (itemsEntity.getInvoiceDetails() != null) {
//            itemsDTO.setInvoiceDetails(itemsEntity.getInvoiceDetails().stream().map(InvoiceDetailMapper::toDTO).collect(Collectors.toList()));
//        }
//        if (itemsEntity.getOrderDetails() != null) {
//            itemsDTO.setOrderDetails(itemsEntity.getOrderDetails().stream().map(OrderDetailMapper::toDTO).collect(Collectors.toList()));
//        }
        return itemsDTO;
    }

    public static Items toEntity(ItemsDTO itemsDTO) {
        Items itemsEntity = Items.builder()
                .id(itemsDTO.getId())
                .price(itemsDTO.getPrice())
                .createDate(itemsDTO.getCreateDate())
                .updateDate(itemsDTO.getUpdateDate())
                .activeFlag(itemsDTO.getActiveFlag())
                .build();
        if (itemsDTO.getProductInStock() != null) {
            itemsEntity.setProductInStock(ProductInStockMapper.toEntity(itemsDTO.getProductInStock()));
        }
//        if (itemsDTO.getInvoiceDetails() != null) {
//            itemsEntity.setInvoiceDetails(itemsDTO.getInvoiceDetails().stream().map(InvoiceDetailMapper::toEntity).collect(Collectors.toList()));
//        }
//        if (itemsDTO.getOrderDetails() != null) {
//            itemsEntity.setOrderDetails(itemsDTO.getOrderDetails().stream().map(OrderDetailMapper::toEntity).collect(Collectors.toList()));
//        }
        return itemsEntity;
    }
}
