package edu.mangement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
@Configuration
public class DateWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer year;
    private Integer month;
    private Integer snn;
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
    @ManyToOne
    @JoinColumn(name = "Member_ID", referencedColumnName = "id", nullable = false)
    private Member member;
}
