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
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/15/2020
 * TIME : 11:03 PM
 */
@Entity
@Table(name = "Invoice_Detail", schema = "dbo", catalog = "CKTDDQ")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Configuration
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quantity;
    private BigDecimal unitPrice;
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
    @ManyToOne
    @JoinColumn(name = "Items_ID", referencedColumnName = "id", nullable = false)
    private Items items;
    @ManyToOne
    @JoinColumn(name = "Invoice_ID", referencedColumnName = "id", nullable = false)
    private Invoice invoice;
}
