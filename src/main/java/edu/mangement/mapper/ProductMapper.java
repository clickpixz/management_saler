package edu.mangement.mapper;

import edu.mangement.entity.Product;
import edu.mangement.model.dto.ProductDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 6:02 PM
 */
public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .image(product.getImage())
                .material(product.getMaterial())
                .description(product.getDescription())
                .createDate(product.getCreateDate())
                .updateDate(product.getUpdateDate())
                .activeFlag(product.getActiveFlag())
                .vendor(VendorMapper.toDTO(product.getVendor()))
                .category(CategoryMapper.toDTO(product.getCategory()))
                .productInStocks(product.getProductInStocks().stream().map(ProductInStockMapper::toDTO).collect(Collectors.toList()))
                .build();
    }

    public static Product toEntity(ProductDTO product) {
        return Product.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .image(product.getImage())
                .material(product.getMaterial())
                .createDate(product.getCreateDate())
                .updateDate(product.getUpdateDate())
                .activeFlag(product.getActiveFlag())
                .description(product.getDescription())
                .vendor(VendorMapper.toEntity(product.getVendor()))
                .category(CategoryMapper.toEntity(product.getCategory()))
                .productInStocks(product.getProductInStocks().stream().map(ProductInStockMapper::toEntity).collect(Collectors.toList()))
                .build();
    }
}
