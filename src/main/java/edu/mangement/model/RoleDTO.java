package edu.mangement.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 1:53 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDTO {
    private Long id;
    @NotBlank
    private String name;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
}
