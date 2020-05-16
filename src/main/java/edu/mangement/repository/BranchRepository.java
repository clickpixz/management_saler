package edu.mangement.repository;

import edu.mangement.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:45 AM
 */
public interface BranchRepository extends JpaRepository<Branch,Long> {
}
