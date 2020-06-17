package edu.mangement.entity;

import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
@ToString(exclude = "productInStocks")
@EntityListeners(AuditingEntityListener.class)
@Indexed
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "Product.getTotalQuantityProductSell",
                procedureName = "Sp_getTotalQuantityProductSell"
        ),
        @NamedStoredProcedureQuery(
                name = "Product.countProductSoldInYear",
                procedureName = "Sp_countProductSoldInYear",
                resultSetMappings = "NewCustomerByWeek",
                parameters = {
                        @StoredProcedureParameter(
                                name = "DATE_FROM",
                                type = Date.class,
                                mode = ParameterMode.IN
                        ),
                        @StoredProcedureParameter(
                                name = "productId",
                                type = Long.class,
                                mode = ParameterMode.IN
                        )
                }
        ),
        @NamedStoredProcedureQuery(
                name = "Product.countProductSoldInYear_2",
                procedureName = "Sp_countProductSoldInYear_2",
                resultSetMappings = "NewCustomerByWeek",
                parameters = {
                        @StoredProcedureParameter(
                                name = "DATE_FROM",
                                type = Date.class,
                                mode = ParameterMode.IN
                        ),
                        @StoredProcedureParameter(
                                name = "productId",
                                type = Long.class,
                                mode = ParameterMode.IN
                        )
                }
        )

})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Field(termVector = TermVector.YES)
    private String name;
    @Field(termVector = TermVector.YES)
    private String code;
    @Field(termVector = TermVector.YES)
    private String material;
    private String image;
    @Field(termVector = TermVector.YES)
    private String description;
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
    private Integer activeFlag;
    @ManyToOne
    @JoinColumn(name = "Category_ID", referencedColumnName = "id", nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "Vendor_ID", referencedColumnName = "id", nullable = false)
    private Vendor vendor;
    @OneToMany(mappedBy = "product")
    private List<ProductInStock> productInStocks;
}
