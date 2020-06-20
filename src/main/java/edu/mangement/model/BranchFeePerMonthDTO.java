package edu.mangement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 2:01 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchFeePerMonthDTO {
    private Long id;
    private String description;
    @Min(value = 0,message = "cost > 0")
    private BigDecimal cost;
    @Min(value = 0,message = "month > 0")
    @Max(value = 12,message = "month <=12")
    private Integer month;
    @Min(value = 2017,message = "year >= 2018")
    @Max(value = 2022,message = "year <=2022")
    private Integer year;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private Date updateDate;
    private Long branchId;
    private String branchName;
    private Integer activeFlag;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;
}
