package edu.mangement.entity;

import lombok.*;

import javax.persistence.*;
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
    private List<BranchFeePerMonth> branchFeePerMonths;
    @OneToMany(mappedBy = "branch")
    private List<Member> members;
    @OneToMany(mappedBy = "branch")
    private List<ProductInStock> productInStocks;
}
