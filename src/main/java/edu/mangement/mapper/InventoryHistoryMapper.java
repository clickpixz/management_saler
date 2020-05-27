package edu.mangement.mapper;

import edu.mangement.entity.InventoryHistory;
import edu.mangement.model.InventoryHistoryDTO;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:51 PM
 */
public class InventoryHistoryMapper {
    public static InventoryHistoryDTO toDTO(InventoryHistory inventoryHistoryEntity) {
        InventoryHistoryDTO inventoryHistoryDTO = InventoryHistoryDTO.builder()
                .id(inventoryHistoryEntity.getId())
                .code(inventoryHistoryEntity.getCode())
                .type(inventoryHistoryEntity.getType())
                .description(inventoryHistoryEntity.getDescription())
                .quantity(inventoryHistoryEntity.getQuantity())
                .createDate(inventoryHistoryEntity.getCreateDate())
                .updateDate(inventoryHistoryEntity.getUpdateDate())
                .activeFlag(inventoryHistoryEntity.getActiveFlag())
                .build();
        if (inventoryHistoryEntity.getMember() != null) {
            inventoryHistoryDTO.setMember(MemberMapper.toDTO(inventoryHistoryEntity.getMember()));
        }
        if (inventoryHistoryEntity.getProductInStock() != null) {
            inventoryHistoryDTO.setProductInStock(ProductInStockMapper.toDTO(inventoryHistoryEntity.getProductInStock()));
        }
        return inventoryHistoryDTO;
    }

    public static InventoryHistory toEntity(InventoryHistoryDTO inventoryHistoryDTO) {
        InventoryHistory inventoryHistoryEntity = InventoryHistory.builder()
                .id(inventoryHistoryDTO.getId())
                .code(inventoryHistoryDTO.getCode())
                .description(inventoryHistoryDTO.getDescription())
                .type(inventoryHistoryDTO.getType())
                .quantity(inventoryHistoryDTO.getQuantity())
                .createDate(inventoryHistoryDTO.getCreateDate())
                .updateDate(inventoryHistoryDTO.getUpdateDate())
                .activeFlag(inventoryHistoryDTO.getActiveFlag())
                .build();
        if (inventoryHistoryDTO.getMember() != null) {
            inventoryHistoryEntity.setMember(MemberMapper.toEntity(inventoryHistoryDTO.getMember()));
        }
        if (inventoryHistoryDTO.getProductInStock() != null) {
            inventoryHistoryEntity.setProductInStock(ProductInStockMapper.toEntity(inventoryHistoryDTO.getProductInStock()));
        }
        return inventoryHistoryEntity;
    }
}
