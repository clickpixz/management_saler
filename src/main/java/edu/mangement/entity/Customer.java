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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
@ToString(exclude = "orders")
@EntityListeners(AuditingEntityListener.class)
@Indexed
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Field(termVector = TermVector.YES)
    private String username;
    @Field(termVector = TermVector.YES)
    private String password;
    @Field(termVector = TermVector.YES)
    private String name;
    @Field(termVector = TermVector.YES)
    private String address;
    @Field(termVector = TermVector.YES)
    private String email;
    @Field(termVector = TermVector.YES)
    private String image;
    @Field(termVector = TermVector.YES)
    private String phone;
    @Field(termVector = TermVector.YES)
    private String birthday;
    @CreatedDate
    @Field(termVector = TermVector.YES)
    private Date createDate;
    @LastModifiedDate
    @Field(termVector = TermVector.YES)
    private Date updateDate;
    private Integer activeFlag;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
