package edu.mangement.mapper;

import edu.mangement.entity.Category;
import edu.mangement.model.CategoryDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:49 PM
 */
public class CategoryMapper {
    public static CategoryDTO toDTO(Category categoryEntity) {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .parentId(categoryEntity.getParentId())
                .createDate(categoryEntity.getCreateDate())
                .updateDate(categoryEntity.getUpdateDate())
                .activeFlag(categoryEntity.getActiveFlag())
                .build();
        return categoryDTO;
    }

    public static Category toEntity(CategoryDTO categoryDTO) {
        Category categoryEntity = Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .parentId(categoryDTO.getParentId())
                .createDate(categoryDTO.getCreateDate())
                .updateDate(categoryDTO.getUpdateDate())
                .activeFlag(categoryDTO.getActiveFlag())
                .build();
        return categoryEntity;
    }
}
