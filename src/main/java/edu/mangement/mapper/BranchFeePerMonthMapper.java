package edu.mangement.mapper;

import edu.mangement.entity.Branch;
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
        return BranchFeePerMonthDTO.builder()
                .id(branchFeePerMonth.getId())
                .year(branchFeePerMonth.getYear())
                .cost(branchFeePerMonth.getCost())
                .month(branchFeePerMonth.getMonth())
                .description(branchFeePerMonth.getDescription())
                .branchId(branchFeePerMonth.getBranch().getId())
                .branchName(branchFeePerMonth.getBranch().getName())
                .createDate(branchFeePerMonth.getCreateDate())
                .updateDate(branchFeePerMonth.getUpdateDate())
                .activeFlag(branchFeePerMonth.getActiveFlag())
                .build();
    }

    public static BranchFeePerMonth toEntity(BranchFeePerMonthDTO branchFeePerMonth) {
        return  BranchFeePerMonth.builder()
                .id(branchFeePerMonth.getId())
                .cost(branchFeePerMonth.getCost())
                .description(branchFeePerMonth.getDescription())
                .month(branchFeePerMonth.getMonth())
                .year(branchFeePerMonth.getYear())
                .branch(Branch.builder().id(branchFeePerMonth.getBranchId()).build())
                .createDate(branchFeePerMonth.getCreateDate())
                .updateDate(branchFeePerMonth.getUpdateDate())
                .activeFlag(branchFeePerMonth.getActiveFlag())
                .build();
    }
}
