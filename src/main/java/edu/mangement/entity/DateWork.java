package edu.mangement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/15/2020
 * TIME : 11:03 PM
 */
@Entity
@Table(name = "Date_Work", schema = "dbo", catalog = "CKTDDQ")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Indexed
public class DateWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Field(termVector = TermVector.YES)
    private Integer year;
    @Field(termVector = TermVector.YES)
    private Integer month;
    @Field(termVector = TermVector.YES)
    private Integer snn;
    @CreatedDate
    @Field(termVector = TermVector.YES)
    private Date createDate;
    @LastModifiedDate
    @Field(termVector = TermVector.YES)
    private Date updateDate;
    private Integer activeFlag;
    @ManyToOne
    @JoinColumn(name = "Member_ID", referencedColumnName = "id", nullable = false)
    private Member member;
}
