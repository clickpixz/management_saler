package edu.mangement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

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
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productInStockId;
    private BigDecimal price;
    private Date createDate;
    private Date updateDate;
    private Integer activeFlag;
    @OneToMany(mappedBy = "items")
    private Collection<InvoiceDetail> invoiceDetails;
    @ManyToOne
    @JoinColumn(name = "Product_In_Stock_ID", referencedColumnName = "id", nullable = false)
    private ProductInStock productInStock;
    @OneToMany(mappedBy = "items")
    private Collection<OrderDetail> orderDetails;
}
