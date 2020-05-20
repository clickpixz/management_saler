package edu.mangement.mapper;

import edu.mangement.entity.Role;
import edu.mangement.model.RoleDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:28 PM
 */
public class RoleMapper {
    public static RoleDTO toDTO(Role roleEntity) {
        RoleDTO roleDTO = RoleDTO.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .description(roleEntity.getDescription())
                .createDate(roleEntity.getCreateDate())
                .updateDate(roleEntity.getUpdateDate())
                .activeFlag(roleEntity.getActiveFlag())
                .build();
//        if (roleEntity.getAuths() != null) {
//            roleDTO.setAuths(roleEntity.getAuths().stream().map(AuthMapper::toDTO).collect(Collectors.toList()));
//        }
//        if (roleEntity.getMembers() != null) {
//            roleDTO.setMembers(roleEntity.getMembers().stream().map(MemberMapper::toDTO).collect(Collectors.toList()));
//        }
        return roleDTO;
    }

    public static Role toEntity(RoleDTO roleDTO) {
        Role roleEntity = Role.builder()
                .id(roleDTO.getId())
                .name(roleDTO.getName())
                .description(roleDTO.getDescription())
                .createDate(roleDTO.getCreateDate())
                .updateDate(roleDTO.getUpdateDate())
                .activeFlag(roleDTO.getActiveFlag())
                .build();
//        if (roleDTO.getAuths() != null) {
//            roleEntity.setAuths(roleDTO.getAuths().stream().map(AuthMapper::toEntity).collect(Collectors.toList()));
//        }
//        if (roleDTO.getMembers() != null) {
//            roleEntity.setMembers(roleDTO.getMembers().stream().map(MemberMapper::toEntity).collect(Collectors.toList()));
//        }
        return roleEntity;
    }
}
