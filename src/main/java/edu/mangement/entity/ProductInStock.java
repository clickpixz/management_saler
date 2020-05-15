package edu.mangement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

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
public class ProductInStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long branchId;
    private String size;
    private Long quantity;
    private Date createDate;
    private Date updateDate;
    private Integer activeFlag;
    @OneToMany(mappedBy = "productInStock")
    private Collection<InventoryHistory> inventoryHistories;
    @OneToMany(mappedBy = "productInStock")
    private Collection<Items> itemsById;
    @ManyToOne
    @JoinColumn(name = "Product_ID", referencedColumnName = "id", nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "Branch_ID", referencedColumnName = "id", nullable = false)
    private Branch branch;
}
