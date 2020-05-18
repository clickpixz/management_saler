package edu.mangement.mapper.model;

import edu.mangement.entity.BranchFeePerMonth;
import edu.mangement.model.dto.BranchFeePerMonthDTO;
import org.springframework.stereotype.Component;

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
                .cost(branchFeePerMonth.getCost())
                .description(branchFeePerMonth.getDescription())
                .month(branchFeePerMonth.getMonth())
                .year(branchFeePerMonth.getYear())
                .createDate(branchFeePerMonth.getCreateDate())
                .updateDate(branchFeePerMonth.getUpdateDate())
                .activeFlag(branchFeePerMonth.getActiveFlag())
                .branch(BranchMapper.toDTO(branchFeePerMonth.getBranch()))
                .build();
    }
}
