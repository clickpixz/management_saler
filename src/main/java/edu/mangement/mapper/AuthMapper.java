package edu.mangement.mapper;

import edu.mangement.entity.Auth;
import edu.mangement.model.AuthDTO;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:23 PM
 */
public class AuthMapper {

    public static AuthDTO toDTO(Auth auth) {
        AuthDTO authDTO = AuthDTO.builder()
                .id(auth.getId())
                .permission(auth.getPermission())
                .createDate(auth.getCreateDate())
                .updateDate(auth.getUpdateDate())
                .activeFlag(auth.getActiveFlag())
                .build();
//        if (auth.getRole() != null) {
//            authDTO.setRole(RoleMapper.toDTO(auth.getRole()));
//        }
        if (auth.getMenu() != null) {
            authDTO.setFunction(MenuMapper.toDTO(auth.getMenu()));
        }
        return authDTO;
    }

    public static Auth toEntity(AuthDTO auth) {
        Auth authEntity = Auth.builder()
                .id(auth.getId())
                .permission(auth.getPermission())
                .createDate(auth.getCreateDate())
                .updateDate(auth.getUpdateDate())
                .activeFlag(auth.getActiveFlag())
                .build();
//        if (auth.getRole() != null) {
//            authEntity.setRole(RoleMapper.toEntity(auth.getRole()));
//        }
        if (auth.getFunction() != null) {
            authEntity.setMenu(MenuMapper.toEntity(auth.getFunction()));
        }
        return authEntity;
    }
}
