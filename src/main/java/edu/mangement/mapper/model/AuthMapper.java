package edu.mangement.mapper.model;

import edu.mangement.entity.Auth;
import edu.mangement.entity.Function;
import edu.mangement.entity.Role;
import edu.mangement.model.dto.AuthDTO;
import edu.mangement.model.dto.FunctionDTO;
import edu.mangement.model.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
}
