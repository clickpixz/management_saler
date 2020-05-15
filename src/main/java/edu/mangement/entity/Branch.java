package edu.mangement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

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
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Date createDate;
    private Date updateDate;
    private Integer activeFlag;
    @OneToMany(mappedBy = "branch")
    private Collection<BranchFeePerMonth> branchFeePerMonths;
    @OneToMany(mappedBy = "branch")
    private Collection<Member> members;
    @OneToMany(mappedBy = "branch")
    private Collection<ProductInStock> productInStocks;
}
