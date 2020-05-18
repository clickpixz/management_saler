package edu.mangement.mapper;

import edu.mangement.entity.Branch;
import edu.mangement.model.dto.BranchDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:47 PM
 */
public class BranchMapper {
    public static BranchDTO toDTO(Branch branch) {
        return BranchDTO.builder()
                .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .createDate(branch.getCreateDate())
                .updateDate(branch.getUpdateDate())
                .activeFlag(branch.getActiveFlag())
                .branchFeePerMonths(branch.getBranchFeePerMonths().stream().map(BranchFeePerMonthMapper::toDTO).collect(Collectors.toList()))
                .members(branch.getMembers().stream().map(MemberMapper::toDTO).collect(Collectors.toList()))
                .productInStocks(branch.getProductInStocks().stream().map(ProductInStockMapper::toDTO).collect(Collectors.toList()))
                .build();
    }

    public static Branch toEntity(BranchDTO branch) {
        return Branch.builder()
                .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .createDate(branch.getCreateDate())
                .updateDate(branch.getUpdateDate())
                .activeFlag(branch.getActiveFlag())
                .branchFeePerMonths(branch.getBranchFeePerMonths().stream().map(BranchFeePerMonthMapper::toEntity).collect(Collectors.toList()))
                .members(branch.getMembers().stream().map(MemberMapper::toEntity).collect(Collectors.toList()))
                .productInStocks(branch.getProductInStocks().stream().map(ProductInStockMapper::toEntity).collect(Collectors.toList()))
                .build();
    }
}
