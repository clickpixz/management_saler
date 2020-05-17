package edu.mangement.dto;

import edu.mangement.entity.InventoryHistory;
import edu.mangement.entity.Items;
import edu.mangement.entity.Product;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 2:05 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"inventoryHistories","items"})
@Configuration
public class ProductInStockDTO {
    private Long id;
    private String size;
    private Long quantity;
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
    private List<InventoryHistory> inventoryHistories;
    private List<Items> items;
    private Product product;
    private BranchDTO branch;
}
