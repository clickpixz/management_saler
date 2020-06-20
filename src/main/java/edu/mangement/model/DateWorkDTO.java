package edu.mangement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private Date updateDate;
    private Integer activeFlag;
    private Long branchId;
    private String branchName;
    private Long memberId;
    private String memberName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;
}
