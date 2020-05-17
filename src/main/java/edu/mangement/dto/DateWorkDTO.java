package edu.mangement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 2:04 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DateWorkDTO {
    private Long id;
    private Integer year;
    private Integer month;
    private Integer snn;
    private Date createDate;
    private Date updateDate;
    private Integer activeFlag;
    private MemberDTO member;
}
