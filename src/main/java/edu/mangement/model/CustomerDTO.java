package edu.mangement.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 4:31 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Configuration
public class CustomerDTO {
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String name;
    private String address;
    @Email
    private String email;
    private String image;
    private String phone;
    private String birthday;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
}
