package edu.mangement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/15/2020
 * TIME : 11:03 PM
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    private Long id;
    private String name;
    private String description;
    private Date createDate;
    private Date updateDate;
    private Integer activeFlag;
    @OneToMany(mappedBy = "role")
    private Collection<Auth> auths;
    @OneToMany(mappedBy = "role")
    private Collection<Member> members;
}
