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
@Table(name = "Product_In_Stock", schema = "dbo", catalog = "CKTDDQ")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"inventoryHistories","items"})
@EntityListeners(AuditingEntityListener.class)
@Indexed
@NamedQueries(
        @NamedQuery(name = "ProductInStock.checkProductInStockExits",
                query = "SELECT p FROM ProductInStock p where p.product.code =:productCode" +
                        " and p.size =:size and p.branch.id =:branchId and p.price =:price and " +
                        "p.activeFlag =:activeFlag"
        )
)
public class ProductInStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Field(termVector = TermVector.YES)
    private String size;
    @Field(termVector = TermVector.YES)
    private Long quantity;
    @Field(termVector = TermVector.YES)
    private BigDecimal price;
    @CreatedDate
    @Field(termVector = TermVector.YES)
    private Date createDate;
    @LastModifiedDate
    @Field(termVector = TermVector.YES)
    private Date updateDate;
    private Integer activeFlag;
    @OneToMany(mappedBy = "productInStock")
    private List<InventoryHistory> inventoryHistories;
    @OneToMany(mappedBy = "productInStock")
    private List<Items> items;
    @ManyToOne
    @JoinColumn(name = "Product_ID", referencedColumnName = "id", nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "Branch_ID", referencedColumnName = "id", nullable = false)
    private Branch branch;
}
