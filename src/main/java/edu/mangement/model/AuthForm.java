package edu.mangement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/23/2020
 * TIME : 3:09 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthForm {
    @Min(value = 0,message = "select role")
    private Long roleId;
    @Min(value = 0,message = "select menuId")
    private Long menuId;
    private Integer permission;
}
