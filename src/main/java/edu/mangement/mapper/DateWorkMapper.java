package edu.mangement.mapper;

import edu.mangement.entity.DateWork;
import edu.mangement.model.DateWorkDTO;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:50 PM
 */
public class DateWorkMapper {
    public static DateWorkDTO toDTO(DateWork dateWorkEntity) {
        DateWorkDTO dateWorkDTO = DateWorkDTO.builder()
                .id(dateWorkEntity.getId())
                .snn(dateWorkEntity.getSnn())
                .year(dateWorkEntity.getYear())
                .month(dateWorkEntity.getMonth())
                .createDate(dateWorkEntity.getCreateDate())
                .updateDate(dateWorkEntity.getUpdateDate())
                .activeFlag(dateWorkEntity.getActiveFlag())
                .build();
//        if (dateWorkEntity.getMember() != null) {
//            dateWorkDTO.setMember(MemberMapper.toDTO(dateWorkEntity.getMember()));
//        }
        return dateWorkDTO;
    }

    public static DateWork toEntity(DateWorkDTO dateWorkDTO) {
        DateWork dateWorkEntity = DateWork.builder()
                .id(dateWorkDTO.getId())
                .snn(dateWorkDTO.getSnn())
                .year(dateWorkDTO.getYear())
                .month(dateWorkDTO.getMonth())
                .createDate(dateWorkDTO.getCreateDate())
                .updateDate(dateWorkDTO.getUpdateDate())
                .activeFlag(dateWorkDTO.getActiveFlag())
                .build();
//        if (dateWorkDTO.getMember() != null) {
//            dateWorkEntity.setMember(MemberMapper.toEntity(dateWorkDTO.getMember()));
//        }
        return dateWorkEntity;
    }
}
