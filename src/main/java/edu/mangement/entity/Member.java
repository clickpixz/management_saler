package edu.mangement.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
@ToString(exclude = {"dateWorks","inventoryHistories","invoices"})
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private Integer sex;
    private String doB;
    private String image;
    private BigDecimal salary;
    private Date createDate;
    private Date updateDate;
    private Integer activeFlag;
    @OneToMany(mappedBy = "member")
    private List<DateWork> dateWorks;
    @OneToMany(mappedBy = "member")
    private List<InventoryHistory> inventoryHistories;
    @OneToMany(mappedBy = "member")
    private List<Invoice> invoices;
    @ManyToOne
    @JoinColumn(name = "Branch_ID", referencedColumnName = "id", nullable = false)
    private Branch branch;
    @ManyToOne
    @JoinColumn(name = "Role_ID", referencedColumnName = "id", nullable = false)
    private Role role;
}
