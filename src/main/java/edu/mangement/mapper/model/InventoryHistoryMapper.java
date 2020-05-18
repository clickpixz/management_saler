package edu.mangement.mapper.model;

import edu.mangement.entity.InventoryHistory;
import edu.mangement.model.dto.InventoryHistoryDTO;
import org.springframework.stereotype.Component;

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
}
