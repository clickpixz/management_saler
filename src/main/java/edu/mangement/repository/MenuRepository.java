package edu.mangement.repository;

import edu.mangement.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:50 AM
 */
public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findMenuUse(Long roleId);
}
