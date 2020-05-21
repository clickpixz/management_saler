package edu.mangement.mapper;

import edu.mangement.entity.Branch;
import edu.mangement.model.BranchDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:47 PM
 */
public class BranchMapper {
    public static BranchDTO toDTO(Branch branchEntity) {
        BranchDTO branchDTO = BranchDTO.builder()
                .id(branchEntity.getId())
                .name(branchEntity.getName())
                .address(branchEntity.getAddress())
                .phone(branchEntity.getPhone())
                .createDate(branchEntity.getCreateDate())
                .updateDate(branchEntity.getUpdateDate())
                .activeFlag(branchEntity.getActiveFlag())
                .build();
        return branchDTO;
    }

    public static Branch toEntity(BranchDTO branchDTO) {
        Branch branchEntity = Branch.builder()
                .id(branchDTO.getId())
                .name(branchDTO.getName())
                .address(branchDTO.getAddress())
                .phone(branchDTO.getPhone())
                .createDate(branchDTO.getCreateDate())
                .updateDate(branchDTO.getUpdateDate())
                .activeFlag(branchDTO.getActiveFlag())
                .build();
        return branchEntity;
    }
}
