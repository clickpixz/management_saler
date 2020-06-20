package edu.mangement.repository;

import edu.mangement.entity.Menu;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:50 AM
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findMenuUse(Long roleId);
    Page<Menu> findAllBymType(Integer mType,Pageable pageable);
}
