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
import javax.validation.constraints.NotBlank;
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
@ToString(exclude = "products")
@EntityListeners(AuditingEntityListener.class)
@Indexed
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Field(termVector = TermVector.YES)
    private String name;
    @Field(termVector = TermVector.YES)
    private Integer parentId;
    @CreatedDate
    @Field(termVector = TermVector.YES)
    private Date createDate;
    @LastModifiedDate
    @Field(termVector = TermVector.YES)
    private Date updateDate;
    private Integer activeFlag;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
