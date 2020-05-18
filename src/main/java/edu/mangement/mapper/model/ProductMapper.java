package edu.mangement.mapper.model;

import edu.mangement.entity.Product;
import edu.mangement.model.dto.ProductDTO;
import org.springframework.stereotype.Component;

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
}
