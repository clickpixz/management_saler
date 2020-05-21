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
    @Min(value = 1970,message = "year > 1970")
    private Integer year;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
}
