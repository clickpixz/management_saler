package edu.mangement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

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
public class ProductInStockDTO {
    private Long id;
    @NotBlank(message = "Size not null")
    private String size;
    @Min(value = 0,message = "Quanity must be greater than 0")
    private Long quantity;
    @Min(value = 0,message = "Price must be greater than 0")
    private BigDecimal price;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private Date updateDate;
    private Integer activeFlag;
    private ProductDTO product;
    private BranchDTO branch;
    private Long productId;
    private Long branchId;
}
