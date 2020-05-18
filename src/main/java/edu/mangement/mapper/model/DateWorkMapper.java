package edu.mangement.mapper.model;

import edu.mangement.entity.DateWork;
import edu.mangement.model.dto.DateWorkDTO;
import org.springframework.stereotype.Component;

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
                .year(dateWork.getYear())
                .month(dateWork.getMonth())
                .snn(dateWork.getSnn())
                .createDate(dateWork.getCreateDate())
                .updateDate(dateWork.getUpdateDate())
                .activeFlag(dateWork.getActiveFlag())
                .member(MemberMapper.toDTO(dateWork.getMember()))
                .build();
    }
}
