package edu.mangement.repository;

import edu.mangement.entity.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:45 AM
 */
public interface BranchRepository extends JpaRepository<Branch,Long> {
    List<Branch> findAllByActiveFlag(int activeFlag, Pageable pageable);
}
