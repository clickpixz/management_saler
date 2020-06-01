package edu.mangement.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
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
public class ItemsDTO {
    private Long id;
    @Min(value = 0,message = "Price must be greater than 0 ")
    private BigDecimal price;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private Date updateDate;
    private Integer activeFlag;
    private ProductInStockDTO productInStock;
    private Long productInStockId;
}
