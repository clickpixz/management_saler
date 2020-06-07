package edu.mangement.entity;

import edu.mangement.entity.sp.DayQuantityMapper;
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
@Table(name = "Orders", schema = "dbo", catalog = "CKTDDQ")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "orderDetails")
@EntityListeners(AuditingEntityListener.class)
@Indexed
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "Order.countOrderByDay",
                procedureName = "Sp_CountOrderByDay",
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
@SqlResultSetMapping(
        name = "DayQuantityMapper",
        classes = {
                @ConstructorResult(
                        targetClass = DayQuantityMapper.class,
                        columns = {
                                @ColumnResult(name = "DAY_IN_WEEK", type = String.class),
                                @ColumnResult(name = "quantity", type = Long.class)
                        }
                )
        }
)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Field(termVector = TermVector.YES)
    private BigDecimal totalOrder;
    @Field(termVector = TermVector.YES)
    private String description;
    @Field(termVector = TermVector.YES)
    private String deliveryAddress;
    @Field(termVector = TermVector.YES)
    private Integer statusDelivery;
    @CreatedDate
    @Field(termVector = TermVector.YES)
    private Date createDate;
    @LastModifiedDate
    @Field(termVector = TermVector.YES)
    private Date updateDate;
    private Integer activeFlag;
    @ManyToOne
    @JoinColumn(name = "Customer_ID", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
