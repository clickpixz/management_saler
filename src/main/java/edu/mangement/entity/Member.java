package edu.mangement.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
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
@ToString(exclude = {"dateWorks", "inventoryHistories", "invoices"})
@EntityListeners(AuditingEntityListener.class)
@Configuration
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Username Not Null")
    @Size(min = 6,max = 100,message = "Username Length must be more than 6 character")
    private String username;
    @NotBlank(message = "Password Not Null")
    @Size(min = 6,max = 255,message = "Password Length must be more than 6 character")
    private String password;
    @NotBlank(message = "Name Not Null")
    private String name;
    private Integer sex;
    private String doB;
    private String image;
    @Min(value = 0, message = "Salary must be more than 0")
    private BigDecimal salary;
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
    @Value("1")
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
