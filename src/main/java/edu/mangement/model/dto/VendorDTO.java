package edu.mangement.model.dto;

import edu.mangement.model.dto.ProductDTO;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 4:43 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "products")
@Configuration
public class VendorDTO {
    private Long id;
    @NotBlank
    private String name;
    private String address;
    private String phone;
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
    private List<ProductDTO> products;
}
