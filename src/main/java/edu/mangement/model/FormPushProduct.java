package edu.mangement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/31/2020
 * TIME : 3:33 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormPushProduct {
    private Long productInStockId;
    private BigDecimal price;
}
