package edu.mangement.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 1:44 AM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"dateWorks"})
@Configuration
public class MemberDTO {
    private Long id;
    @NotBlank(message = "Username Not Null")
    @Size(min = 6,max = 100,message = "Username Length must be more than 6 character")
    private String username;
    @NotBlank(message = "Password Not Null")
    @Size(min = 6,max = 255,message = "Password Length must be more than 6 character")
    private String password;
    @NotBlank(message = "Name Not Null")
    private String name;
    private Integer sex;
    private String doB;
    private String image;
    @Min(value = 0, message = "Salary must be more than 0")
    private BigDecimal salary;
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
    private List<DateWorkDTO> dateWorks;
    private BranchDTO branch;
    private RoleDTO role;
}
