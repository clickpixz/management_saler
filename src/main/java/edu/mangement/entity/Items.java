package edu.mangement.entity;

import lombok.*;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"invoiceDetails","orderDetails"})
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;
    private Date createDate;
    private Date updateDate;
    private Integer activeFlag;
    @OneToMany(mappedBy = "items")
    private List<InvoiceDetail> invoiceDetails;
    @ManyToOne
    @JoinColumn(name = "Product_In_Stock_ID", referencedColumnName = "id", nullable = false)
    private ProductInStock productInStock;
    @OneToMany(mappedBy = "items")
    private List<OrderDetail> orderDetails;
}
