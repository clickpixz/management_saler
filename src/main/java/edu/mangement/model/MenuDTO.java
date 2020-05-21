package edu.mangement.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 1:31 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuDTO {
    private Long id;
    private Long parentId;
    @NotBlank
    private String url;
    private String name;
    private Integer orderIndex;
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
    private String idMenu;
    private List<MenuDTO> child;
}