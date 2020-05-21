package edu.mangement.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/15/2020
 * TIME : 11:03 PM
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"branchFeePerMonths","productInStocks","members"})
@EntityListeners(AuditingEntityListener.class)
@Configuration
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name Not Blank")
    private String name;
    @NotBlank(message = "Address Not Blank")
    private String address;
    private String phone;
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
    private Integer activeFlag = 1;
    @OneToMany(mappedBy = "branch")
    private List<BranchFeePerMonth> branchFeePerMonths;
    @OneToMany(mappedBy = "branch")
    private List<Member> members;
    @OneToMany(mappedBy = "branch")
    private List<ProductInStock> productInStocks;
}
