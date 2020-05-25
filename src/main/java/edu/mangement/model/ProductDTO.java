package edu.mangement.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 4:42 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Configuration
public class ProductDTO {
    private Long id;
    @NotBlank(message = "name not blank")
    private String name;
    @NotBlank(message = "code not null")
    private String code;
    @NotBlank(message = "material not null")
    private String material;
    private MultipartFile multipartFile;
    private String image;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
    private CategoryDTO category;
    private Long categoryId;
    private VendorDTO vendor;
    private Long vendorId;
}
