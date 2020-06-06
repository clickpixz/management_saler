package edu.mangement.entity;

import edu.mangement.entity.sp.TopProductSell;
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
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/15/2020
 * TIME : 11:03 PM
 */
@Entity
@Table(name = "Order_Detail", schema = "dbo", catalog = "CKTDDQ")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Indexed
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "OrderDetail.TopProductSell",
                procedureName = "Sp_getTopProductSalerOnlineByMonth",
                resultSetMappings = "TopProductSell",
                parameters = {
                        @StoredProcedureParameter(
                                name = "DATE_FROM",
                                type = Date.class,
                                mode = ParameterMode.IN
                        )
                }
        ),
})
@SqlResultSetMapping(
        name = "TopProductSell",
        classes = {
                @ConstructorResult(
                        targetClass = TopProductSell.class,
                        columns = {
                                @ColumnResult(name = "product_id", type = Long.class),
                                @ColumnResult(name = "sl", type = Long.class)
                        }
                )
        }
)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Field(termVector = TermVector.YES)
    private Integer quantity;
    @Field(termVector = TermVector.YES)
    private BigDecimal unitPrice;
    @CreatedDate
    @Field(termVector = TermVector.YES)
    private Date createDate;
    @LastModifiedDate
    @Field(termVector = TermVector.YES)
    private Date updateDate;
    private Integer activeFlag;
    @ManyToOne
    @JoinColumn(name = "Order_ID", referencedColumnName = "id", nullable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "Items_ID", referencedColumnName = "id", nullable = false)
    private Items items;
}
