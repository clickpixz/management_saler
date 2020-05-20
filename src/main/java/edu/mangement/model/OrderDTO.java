package edu.mangement.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 4:39 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "orderDetails")
@Configuration
public class OrderDTO {
    private Long id;
    @Min(message = "totalOrder > 0", value = 0)
    private BigDecimal totalOrder;
    private String description;
    private String deliveryAddress;
    private Integer statusDelivery;
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
    private CustomerDTO customer;
    private List<OrderDetailDTO> orderDetails;
}
