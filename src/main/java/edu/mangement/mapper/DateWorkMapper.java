package edu.mangement.mapper;

import edu.mangement.entity.DateWork;
import edu.mangement.model.dto.DateWorkDTO;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:50 PM
 */
public class DateWorkMapper{
    public static DateWorkDTO toDTO(DateWork dateWork) {
        return DateWorkDTO.builder()
                .id(dateWork.getId())
                .snn(dateWork.getSnn())
                .year(dateWork.getYear())
                .month(dateWork.getMonth())
                .createDate(dateWork.getCreateDate())
                .updateDate(dateWork.getUpdateDate())
                .activeFlag(dateWork.getActiveFlag())
                .member(MemberMapper.toDTO(dateWork.getMember()))
                .build();
    }
    public static DateWork toEntity(DateWorkDTO dateWork) {
        return DateWork.builder()
                .id(dateWork.getId())
                .snn(dateWork.getSnn())
                .year(dateWork.getYear())
                .month(dateWork.getMonth())
                .createDate(dateWork.getCreateDate())
                .updateDate(dateWork.getUpdateDate())
                .activeFlag(dateWork.getActiveFlag())
                .member(MemberMapper.toEntity(dateWork.getMember()))
                .build();
    }
}
