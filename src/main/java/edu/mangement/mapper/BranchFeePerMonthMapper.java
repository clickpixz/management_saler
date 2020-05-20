package edu.mangement.mapper;

import edu.mangement.entity.BranchFeePerMonth;
import edu.mangement.model.BranchFeePerMonthDTO;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:48 PM
 */
public class BranchFeePerMonthMapper {
    public static BranchFeePerMonthDTO toDTO(BranchFeePerMonth branchFeePerMonth) {
        BranchFeePerMonthDTO branchFeePerMonthDTO = BranchFeePerMonthDTO.builder()
                .id(branchFeePerMonth.getId())
                .year(branchFeePerMonth.getYear())
                .cost(branchFeePerMonth.getCost())
                .month(branchFeePerMonth.getMonth())
                .createDate(branchFeePerMonth.getCreateDate())
                .updateDate(branchFeePerMonth.getUpdateDate())
                .activeFlag(branchFeePerMonth.getActiveFlag())
                .description(branchFeePerMonth.getDescription())
                .build();
//        if (branchFeePerMonth.getBranch() != null) {
//            branchFeePerMonthDTO.setBranch(BranchMapper.toDTO(branchFeePerMonth.getBranch()));
//        }
        return branchFeePerMonthDTO;

    }

    public static BranchFeePerMonth toEntity(BranchFeePerMonthDTO branchFeePerMonth) {
        BranchFeePerMonth branchFeePerMonthEntity = BranchFeePerMonth.builder()
                .id(branchFeePerMonth.getId())
                .cost(branchFeePerMonth.getCost())
                .description(branchFeePerMonth.getDescription())
                .month(branchFeePerMonth.getMonth())
                .year(branchFeePerMonth.getYear())
                .createDate(branchFeePerMonth.getCreateDate())
                .updateDate(branchFeePerMonth.getUpdateDate())
                .activeFlag(branchFeePerMonth.getActiveFlag())
                .build();
//        if (branchFeePerMonth.getBranch() != null) {
//            branchFeePerMonthEntity.setBranch(BranchMapper.toEntity(branchFeePerMonth.getBranch()));
//        }
        return branchFeePerMonthEntity;
    }
}
