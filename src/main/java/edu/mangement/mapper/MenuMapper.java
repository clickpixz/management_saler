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
        MenuDTO menuDTO = MenuDTO.builder()
                .id(menuEntity.getId())
                .parentId(menuEntity.getParentId())
                .url(menuEntity.getUrl())
                .name(menuEntity.getName())
                .orderIndex(menuEntity.getOrderIndex())
                .createDate(menuEntity.getCreateDate())
                .updateDate(menuEntity.getUpdateDate())
                .activeFlag(menuEntity.getActiveFlag())
                .build();
//        if(menuEntity.getAuths()!=null){
//            functionDTO.setAuths(menuEntity.getAuths().stream().map(AuthMapper::toDTO).collect(Collectors.toList()));
//        }
        return menuDTO;
    }
    public static Menu toEntity(MenuDTO menuDTO) {
        Menu functionEntity = Menu.builder()
                .id(menuDTO.getId())
                .parentId(menuDTO.getParentId())
                .url(menuDTO.getUrl())
                .name(menuDTO.getName())
                .orderIndex(menuDTO.getOrderIndex())
                .createDate(menuDTO.getCreateDate())
                .updateDate(menuDTO.getUpdateDate())
                .activeFlag(menuDTO.getActiveFlag())
                .build();
//        if(functionDTO.getAuths()!=null){
//            functionEntity.setAuths(functionDTO.getAuths().stream().map(AuthMapper::toEntity).collect(Collectors.toList()));
//        }
        return functionEntity;
    }
}
