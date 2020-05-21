package edu.mangement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 4:40 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Configuration
public class OrderDetailDTO {
    private Long id;
    @Min(value = 0,message = "quantity > 0")
    private Integer quantity;
    private BigDecimal unitPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
    private ItemsDTO items;
}
