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
        return itemsEntity;
    }
}
