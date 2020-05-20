package edu.mangement.model;

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
 * TIME : 2:00 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Configuration
public class BranchDTO {
    private Long id;
    @NotBlank(message = "Name Not Blank")
    private String name;
    @NotBlank(message = "Address Not Blank")
    private String address;
    @NotBlank(message = "phone not empty")
    private String phone;
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
}
