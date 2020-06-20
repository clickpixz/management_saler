package edu.mangement.mapper;

import edu.mangement.entity.Menu;
import edu.mangement.model.MenuDTO;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 1:37 AM
 */
public class MenuMapper {

    public static MenuDTO toDTO(Menu menuEntity) {
        return MenuDTO.builder()
                .id(menuEntity.getId())
                .parentId(menuEntity.getParentId())
                .url(menuEntity.getUrl())
                .name(menuEntity.getName())
                .orderIndex(menuEntity.getOrderIndex())
                .mType(menuEntity.getMType())
                .createDate(menuEntity.getCreateDate())
                .updateDate(menuEntity.getUpdateDate())
                .activeFlag(menuEntity.getActiveFlag())
                .build();
    }
    public static Menu toEntity(MenuDTO menuDTO) {
        return  Menu.builder()
                .id(menuDTO.getId())
                .parentId(menuDTO.getParentId())
                .url(menuDTO.getUrl())
                .name(menuDTO.getName())
                .orderIndex(menuDTO.getOrderIndex())
                .mType(menuDTO.getMType())
                .createDate(menuDTO.getCreateDate())
                .updateDate(menuDTO.getUpdateDate())
                .activeFlag(menuDTO.getActiveFlag())
                .build();
    }
}
