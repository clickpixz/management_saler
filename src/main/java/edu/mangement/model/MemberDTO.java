package edu.mangement.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

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
public class MemberDTO {
    private Long id;
    private String username;
    private String password;
    private String name;
    private Integer sex;
    private String doB;
    private String image;
    private MultipartFile multipartFile;
    private BigDecimal salary;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private Date updateDate;
    private Integer activeFlag;
    private List<DateWorkDTO> dateWorks;
    private BranchDTO branch;
    private Long branchId;
    private RoleDTO role;
    private Long roleId;
}
