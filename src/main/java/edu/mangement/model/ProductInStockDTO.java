package edu.mangement.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

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
@Configuration
public class ProductInStockDTO {
    private Long id;
    private String size;
    private Long quantity;
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
    private ProductDTO product;
    private BranchDTO branch;
}
