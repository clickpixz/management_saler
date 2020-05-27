package edu.mangement.entity;

import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
@Indexed
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Field(termVector = TermVector.YES)
    private String username;
    private String password;
    @Field(termVector = TermVector.YES)
    private String name;
    @Field(termVector = TermVector.YES)
    private Integer sex;
    @Field(termVector = TermVector.YES)
    private String doB;
    private String image;
    private BigDecimal salary;
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
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
