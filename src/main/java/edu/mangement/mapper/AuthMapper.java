package edu.mangement.mapper;

import edu.mangement.entity.Auth;
import edu.mangement.model.dto.AuthDTO;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:23 PM
 */
public class AuthMapper{

    public static AuthDTO toDTO(Auth auth) {
        return AuthDTO.builder()
                .id(auth.getId())
                .permission(auth.getPermission())
                .createDate(auth.getCreateDate())
                .updateDate(auth.getUpdateDate())
                .activeFlag(auth.getActiveFlag())
                .role(RoleMapper.toDTO(auth.getRole()))
                .function(FunctionMapper.toDTO(auth.getFunction()))
                .build();
    }
    public static Auth toEntity(AuthDTO auth) {
        return Auth.builder()
                .id(auth.getId())
                .permission(auth.getPermission())
                .createDate(auth.getCreateDate())
                .updateDate(auth.getUpdateDate())
                .activeFlag(auth.getActiveFlag())
                .role(RoleMapper.toEntity(auth.getRole()))
                .function(FunctionMapper.toEntity(auth.getFunction()))
                .build();
    }
}
