package edu.mangement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

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
public class InventoryHistoryDTO {
    private Long id;
    private String code;
    private Integer type;
    private Long quantity;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private Date updateDate;
    private Integer activeFlag;
    private ProductInStockDTO productInStock;
    private MemberDTO member;
}
