package edu.mangement.mapper;

import edu.mangement.entity.Product;
import edu.mangement.model.ProductDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 6:02 PM
 */
public class ProductMapper {
    public static ProductDTO toDTO(Product productEntity) {
        ProductDTO productDTO = ProductDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .code(productEntity.getCode())
                .image(productEntity.getImage())
                .material(productEntity.getMaterial())
                .description(productEntity.getDescription())
                .createDate(productEntity.getCreateDate())
                .updateDate(productEntity.getUpdateDate())
                .activeFlag(productEntity.getActiveFlag())
                .build();
        if (productEntity.getVendor() != null) {
            productDTO.setVendor(VendorMapper.toDTO(productEntity.getVendor()));
        }
        if (productEntity.getCategory() != null) {
            productDTO.setCategory(CategoryMapper.toDTO(productEntity.getCategory()));
        }
//        if (productEntity.getProductInStocks() != null) {
//            productDTO.setProductInStocks(productEntity.getProductInStocks().stream().map(ProductInStockMapper::toDTO).collect(Collectors.toList()));
//        }
        return productDTO;
    }

    public static Product toEntity(ProductDTO productDTO) {
        Product productEntity = Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .code(productDTO.getCode())
                .image(productDTO.getImage())
                .material(productDTO.getMaterial())
                .createDate(productDTO.getCreateDate())
                .updateDate(productDTO.getUpdateDate())
                .activeFlag(productDTO.getActiveFlag())
                .description(productDTO.getDescription())
                .build();
        if (productDTO.getVendor() != null) {
            productEntity.setVendor(VendorMapper.toEntity(productDTO.getVendor()));
        }
        if (productDTO.getCategory() != null) {
            productEntity.setCategory(CategoryMapper.toEntity(productDTO.getCategory()));
        }
//        if (productDTO.getProductInStocks() != null) {
//            productEntity.setProductInStocks(productDTO.getProductInStocks().stream().map(ProductInStockMapper::toEntity).collect(Collectors.toList()));
//        }
        return productEntity;
    }
}
