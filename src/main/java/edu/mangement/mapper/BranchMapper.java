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
//        if (branchEntity.getBranchFeePerMonths()!=null){
//            branchDTO.setBranchFeePerMonths(branchEntity.getBranchFeePerMonths().stream().map(BranchFeePerMonthMapper::toDTO).collect(Collectors.toList()));
//        }
//        if (branchEntity.getMembers()!=null){
//            branchDTO.setMembers(branchEntity.getMembers().stream().map(MemberMapper::toDTO).collect(Collectors.toList()));
//        }
//        if(branchEntity.getProductInStocks()!=null){
//            branchDTO.setProductInStocks(branchEntity.getProductInStocks().stream().map(ProductInStockMapper::toDTO).collect(Collectors.toList()));
//        }
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
//        if (branchDTO.getBranchFeePerMonths()!=null){
//            branchEntity.setBranchFeePerMonths(branchDTO.getBranchFeePerMonths().stream().map(BranchFeePerMonthMapper::toEntity).collect(Collectors.toList()));
//        }
//        if (branchDTO.getMembers()!=null){
//            branchEntity.setMembers(branchDTO.getMembers().stream().map(MemberMapper::toEntity).collect(Collectors.toList()));
//        }
//        if(branchDTO.getProductInStocks()!=null){
//            branchEntity.setProductInStocks(branchDTO.getProductInStocks().stream().map(ProductInStockMapper::toEntity).collect(Collectors.toList()));
//        }
        return branchEntity;
    }
}
