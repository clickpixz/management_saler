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
 * TIME : 4:35 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "invoiceDetails")
@Configuration
public class InvoiceDTO {
    private Long id;
    @Min(value = 0,message = "totalOrder > 0")
    private BigDecimal totalOrder;
    private String nameCustomer;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
    private MemberDTO member;
    private List<InvoiceDetailDTO> invoiceDetails;
}
