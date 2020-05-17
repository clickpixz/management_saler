package edu.mangement.dto.mapper;

import edu.mangement.dto.FunctionDTO;
import edu.mangement.entity.Function;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 1:37 AM
 */
@Component
public class FunctionMapper implements ModelMapper<FunctionDTO,Function>{
    @Override
    public FunctionDTO toDTO(Function function) {
        return FunctionDTO.builder()
                .id(function.getId())
                .parentId(function.getParentId())
                .url(function.getUrl())
                .name(function.getName())
                .orderIndex(function.getOrderIndex())
                .createDate(function.getCreateDate())
                .updateDate(function.getUpdateDate())
                .activeFlag(function.getActiveFlag())
                .build();
//        return null;
    }
}
