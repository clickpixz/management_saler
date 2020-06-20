package edu.mangement.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private Date updateDate;
    private Integer mType;
    @Value("1")
    private Integer activeFlag;
    private String idMenu;
    private List<MenuDTO> child;
    private Map<Long,Integer> mapAuth;
}
