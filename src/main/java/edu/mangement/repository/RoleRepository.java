package edu.mangement.repository;

import edu.mangement.entity.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:59 AM
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findAllByActiveFlag(int activeFlag, Pageable pageable);
    Role findRoleByIdAndActiveFlag(Long id,int activeFlag);
}
