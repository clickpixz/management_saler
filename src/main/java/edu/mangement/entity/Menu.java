package edu.mangement.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
@ToString(exclude = "auths")
@EntityListeners(AuditingEntityListener.class)
@Configuration
@NamedQueries(
        @NamedQuery(name = "Menu.findMenuUse",
        query = "SELECT a.menu from Auth a inner join a.menu inner join a.role " +
                "where a.role.id = :roleId and a.role.activeFlag = 1 and a.activeFlag=1 and a.permission =1" +
                " and a.menu.activeFlag=1")
)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long parentId;
    private String url;
    private String name;
    private Integer orderIndex;
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
    @Value("1")
    private Integer activeFlag;
    @OneToMany(mappedBy = "menu")
    private List<Auth> auths;
}