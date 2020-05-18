package edu.mangement.mapper;

import edu.mangement.entity.InventoryHistory;
import edu.mangement.model.dto.InventoryHistoryDTO;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:51 PM
 */
public class InventoryHistoryMapper{
    public static InventoryHistoryDTO toDTO(InventoryHistory inventoryHistory) {
        return InventoryHistoryDTO.builder()
                .id(inventoryHistory.getId())
                .quantity(inventoryHistory.getQuantity())
                .price(inventoryHistory.getPrice())
                .type(inventoryHistory.getType())
                .createDate(inventoryHistory.getCreateDate())
                .updateDate(inventoryHistory.getUpdateDate())
                .activeFlag(inventoryHistory.getActiveFlag())
                .member(MemberMapper.toDTO(inventoryHistory.getMember()))
                .productInStock(ProductInStockMapper.toDTO(inventoryHistory.getProductInStock()))
                .build();
    }

    public static InventoryHistory toEntity(InventoryHistoryDTO inventoryHistory) {
        return InventoryHistory.builder()
                .id(inventoryHistory.getId())
                .quantity(inventoryHistory.getQuantity())
                .price(inventoryHistory.getPrice())
                .type(inventoryHistory.getType())
                .createDate(inventoryHistory.getCreateDate())
                .updateDate(inventoryHistory.getUpdateDate())
                .activeFlag(inventoryHistory.getActiveFlag())
                .member(MemberMapper.toEntity(inventoryHistory.getMember()))
                .productInStock(ProductInStockMapper.toEntity(inventoryHistory.getProductInStock()))
                .build();
    }
}
