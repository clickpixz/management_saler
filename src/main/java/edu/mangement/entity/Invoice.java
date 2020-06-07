package edu.mangement.entity;

import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;
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
@ToString(exclude = "invoiceDetails")
@EntityListeners(AuditingEntityListener.class)
@Indexed
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "Invoice.countInvoiceByDay",
                procedureName = "Sp_countInvoiceByDay",
                resultSetMappings = "DayQuantityMapper",
                parameters = {
                        @StoredProcedureParameter(
                                name = "DATE_FROM",
                                type = Date.class,
                                mode = ParameterMode.IN
                        )
                }
        ),
})
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Field(termVector = TermVector.YES)
    private BigDecimal totalOrder;
    @Field(termVector = TermVector.YES)
    private String nameCustomer;
    @Field(termVector = TermVector.YES)
    private String phone;
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
    @OneToMany(mappedBy = "invoice")
    private List<InvoiceDetail> invoiceDetails;
}
