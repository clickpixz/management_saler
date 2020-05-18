package edu.mangement.model.dto;

import edu.mangement.model.dto.AuthDTO;
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
@ToString(exclude = "auths")
public class FunctionDTO {
    private Long id;
    private Integer parentId;
    @NotBlank
    private String url;
    private String name;
    private Integer orderIndex;
    private String idMenu;
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
    private List<AuthDTO> auths;
}
