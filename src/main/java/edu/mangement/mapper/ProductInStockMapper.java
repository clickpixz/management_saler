package edu.mangement.mapper;

import edu.mangement.entity.ProductInStock;
import edu.mangement.model.dto.ProductInStockDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 6:03 PM
 */
public class ProductInStockMapper {
    public static ProductInStockDTO toDTO(ProductInStock productInStock) {
        return ProductInStockDTO.builder()
                .id(productInStock.getId())
                .quantity(productInStock.getQuantity())
                .size(productInStock.getSize())
                .createDate(productInStock.getCreateDate())
                .updateDate(productInStock.getUpdateDate())
                .activeFlag(productInStock.getActiveFlag())
                .branch(BranchMapper.toDTO(productInStock.getBranch()))
                .product(ProductMapper.toDTO(productInStock.getProduct()))
                .items(productInStock.getItems().stream().map(ItemsMapper::toDTO).collect(Collectors.toList()))
                .inventoryHistories(productInStock.getInventoryHistories().stream().map(InventoryHistoryMapper::toDTO).collect(Collectors.toList()))
                .build();
    }

    public static ProductInStock toEntity(ProductInStockDTO productInStock) {
        return ProductInStock.builder()
                .id(productInStock.getId())
                .quantity(productInStock.getQuantity())
                .size(productInStock.getSize())
                .createDate(productInStock.getCreateDate())
                .updateDate(productInStock.getUpdateDate())
                .activeFlag(productInStock.getActiveFlag())
                .branch(BranchMapper.toEntity(productInStock.getBranch()))
                .product(ProductMapper.toEntity(productInStock.getProduct()))
                .items(productInStock.getItems().stream().map(ItemsMapper::toEntity).collect(Collectors.toList()))
                .inventoryHistories(productInStock.getInventoryHistories().stream().map(InventoryHistoryMapper::toEntity).collect(Collectors.toList()))
                .build();
    }
}
