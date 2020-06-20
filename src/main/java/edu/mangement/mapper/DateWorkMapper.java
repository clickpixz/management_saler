package edu.mangement.mapper;

import edu.mangement.entity.DateWork;
import edu.mangement.entity.Member;
import edu.mangement.model.DateWorkDTO;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:50 PM
 */
public class DateWorkMapper {
    public static DateWorkDTO toDTO(DateWork dateWorkEntity) {
        return DateWorkDTO.builder()
                .id(dateWorkEntity.getId())
                .snn(dateWorkEntity.getSnn())
                .year(dateWorkEntity.getYear())
                .month(dateWorkEntity.getMonth())
                .memberId(dateWorkEntity.getMember().getId())
                .memberName(dateWorkEntity.getMember().getName())
                .branchId(dateWorkEntity.getMember().getBranch().getId())
                .branchName(dateWorkEntity.getMember().getBranch().getName())
                .createDate(dateWorkEntity.getCreateDate())
                .updateDate(dateWorkEntity.getUpdateDate())
                .activeFlag(dateWorkEntity.getActiveFlag())
                .build();
    }

    public static DateWork toEntity(DateWorkDTO dateWorkDTO) {
        return  DateWork.builder()
                .id(dateWorkDTO.getId())
                .snn(dateWorkDTO.getSnn())
                .year(dateWorkDTO.getYear())
                .month(dateWorkDTO.getMonth())
                .member(Member.builder().id(dateWorkDTO.getMemberId()).build())
                .createDate(dateWorkDTO.getCreateDate())
                .updateDate(dateWorkDTO.getUpdateDate())
                .activeFlag(dateWorkDTO.getActiveFlag())
                .build();
    }
}
