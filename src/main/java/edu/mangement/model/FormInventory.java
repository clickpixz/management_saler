package edu.mangement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/30/2020
 * TIME : 4:53 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormInventory {
    private String inventoryCode;
    private String productCode;
    private Integer type;
    private BigDecimal price;
    private String size;
    private Long quantity;
    private String description;
}
