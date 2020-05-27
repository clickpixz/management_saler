package edu.mangement.mapper;

import edu.mangement.entity.ProductInStock;
import edu.mangement.model.ProductInStockDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 6:03 PM
 */
public class ProductInStockMapper {
    public static ProductInStockDTO toDTO(ProductInStock productInStockEntity) {
        ProductInStockDTO productInStockDTO = ProductInStockDTO.builder()
                .id(productInStockEntity.getId())
                .quantity(productInStockEntity.getQuantity())
                .size(productInStockEntity.getSize())
                .createDate(productInStockEntity.getCreateDate())
                .updateDate(productInStockEntity.getUpdateDate())
                .activeFlag(productInStockEntity.getActiveFlag())
                .build();
        if (productInStockEntity.getBranch() != null) {
            productInStockDTO.setBranch(BranchMapper.toDTO(productInStockEntity.getBranch()));
        }
        if (productInStockEntity.getProduct() != null) {
            productInStockDTO.setProduct(ProductMapper.toDTO(productInStockEntity.getProduct()));
        }
        return productInStockDTO;
    }

    public static ProductInStock toEntity(ProductInStockDTO productInStockDTO) {
        ProductInStock productInStockEntity = ProductInStock.builder()
                .id(productInStockDTO.getId())
                .quantity(productInStockDTO.getQuantity())
                .size(productInStockDTO.getSize())
                .createDate(productInStockDTO.getCreateDate())
                .updateDate(productInStockDTO.getUpdateDate())
                .activeFlag(productInStockDTO.getActiveFlag())
                .build();
        if (productInStockDTO.getBranch() != null) {
            productInStockEntity.setBranch(BranchMapper.toEntity(productInStockDTO.getBranch()));
        }
        if (productInStockDTO.getProduct() != null) {
            productInStockEntity.setProduct(ProductMapper.toEntity(productInStockDTO.getProduct()));
        }
//        if (productInStockDTO.getItems() != null) {
//            productInStockEntity.setItems(productInStockDTO.getItems().stream().map(ItemsMapper::toEntity).collect(Collectors.toList()));
//        }
//        if (productInStockDTO.getInventoryHistories() != null) {
//            productInStockEntity.setInventoryHistories(productInStockDTO.getInventoryHistories().stream().map(InventoryHistoryMapper::toEntity).collect(Collectors.toList()));
//        }
        return productInStockEntity;
    }
}
