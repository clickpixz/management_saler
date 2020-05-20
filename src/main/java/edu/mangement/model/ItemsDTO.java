package edu.mangement.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 4:38 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"invoiceDetails","orderDetails"})
@Configuration
public class ItemsDTO {
    private Long id;
    private BigDecimal price;
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
    private ProductInStockDTO productInStock;
}
