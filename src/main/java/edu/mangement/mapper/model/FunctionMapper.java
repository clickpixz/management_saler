package edu.mangement.mapper.model;

import edu.mangement.entity.Auth;
import edu.mangement.model.dto.AuthDTO;
import edu.mangement.model.dto.FunctionDTO;
import edu.mangement.entity.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
