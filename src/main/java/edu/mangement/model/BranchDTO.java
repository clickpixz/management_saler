package edu.mangement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.util.Date;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createDate;
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
}
