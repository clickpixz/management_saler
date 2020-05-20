package edu.mangement.repository;

import edu.mangement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:59 AM
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByIdAndActiveFlag(Long id,Integer activeFlag);
}
