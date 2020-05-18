package edu.mangement.mapper.model;

import edu.mangement.entity.Category;
import edu.mangement.model.dto.CategoryDTO;
import org.springframework.stereotype.Component;

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
}
