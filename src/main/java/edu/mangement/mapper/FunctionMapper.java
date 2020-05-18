package edu.mangement.mapper;

import edu.mangement.model.dto.FunctionDTO;
import edu.mangement.entity.Function;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 1:37 AM
 */
public class FunctionMapper {

    public static FunctionDTO toDTO(Function function) {
        return FunctionDTO.builder()
                .id(function.getId())
                .parentId(function.getParentId())
                .url(function.getUrl())
                .name(function.getName())
                .orderIndex(function.getOrderIndex())
                .createDate(function.getCreateDate())
                .updateDate(function.getUpdateDate())
                .activeFlag(function.getActiveFlag())
                .auths(function.getAuths().stream().map(AuthMapper::toDTO).collect(Collectors.toList()))
                .build();
    }
    public static Function toEntity(FunctionDTO function) {
        return Function.builder()
                .id(function.getId())
                .parentId(function.getParentId())
                .url(function.getUrl())
                .name(function.getName())
                .orderIndex(function.getOrderIndex())
                .createDate(function.getCreateDate())
                .updateDate(function.getUpdateDate())
                .activeFlag(function.getActiveFlag())
                .auths(function.getAuths().stream().map(AuthMapper::toEntity).collect(Collectors.toList()))
                .build();
    }
}
