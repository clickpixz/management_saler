package edu.mangement.mapper.model;

import edu.mangement.entity.Role;
import edu.mangement.model.dto.RoleDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:28 PM
 */
public class RoleMapper  {
    public static RoleDTO toDTO(Role role) {
        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .createDate(role.getCreateDate())
                .updateDate(role.getUpdateDate())
                .activeFlag(role.getActiveFlag())
                .auths(role.getAuths().stream().map(AuthMapper::toDTO).collect(Collectors.toList()))
                .members(role.getMembers().stream().map(MemberMapper::toDTO).collect(Collectors.toList()))
                .build();
    }
}
