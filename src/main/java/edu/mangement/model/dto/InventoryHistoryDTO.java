package edu.mangement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 4:32 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Configuration
public class InventoryHistoryDTO {
    private Long id;
    private Integer type;
    private Long quantity;
    private BigDecimal price;
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
    private ProductInStockDTO productInStock;
    private MemberDTO member;
}
