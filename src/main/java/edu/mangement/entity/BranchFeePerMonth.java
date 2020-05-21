package edu.mangement.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/15/2020
 * TIME : 11:03 PM
 */
@Entity
@Table(name = "Branch_Fee_Permonth", schema = "dbo", catalog = "CKTDDQ")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Configuration
public class BranchFeePerMonth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Min(value = 0,message = "cost > 0")
    private BigDecimal cost;
    @Min(value = 0,message = "month > 0")
    @Max(value = 12,message = "month <=12")
    private Integer month;
    @Min(value = 1970,message = "year > 1970")
    private Integer year;
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
    @Value("1")
    private Integer activeFlag = 1;
    @ManyToOne
    @JoinColumn(name = "Branch_ID", referencedColumnName = "id", nullable = false)
    private Branch branch;
}
