package edu.mangement.mapper;

import edu.mangement.entity.Category;
import edu.mangement.model.dto.CategoryDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:49 PM
 */
public class CategoryMapper {
    public static CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParentId())
                .createDate(category.getCreateDate())
                .updateDate(category.getUpdateDate())
                .activeFlag(category.getActiveFlag())
                .products(category.getProducts().stream().map(ProductMapper::toDTO).collect(Collectors.toList()))
                .build();
    }

    public static Category toEntity(CategoryDTO category) {
        return Category.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParentId())
                .createDate(category.getCreateDate())
                .updateDate(category.getUpdateDate())
                .activeFlag(category.getActiveFlag())
                .products(category.getProducts().stream().map(ProductMapper::toEntity).collect(Collectors.toList()))
                .build();
    }
}
