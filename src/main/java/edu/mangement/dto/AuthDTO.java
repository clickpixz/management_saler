package edu.mangement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 1:52 AM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {
    private Long id;
    private Integer permission;
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
    private RoleDTO role;
    private FunctionDTO function;
}
