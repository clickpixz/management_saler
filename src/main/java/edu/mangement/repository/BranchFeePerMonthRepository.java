package edu.mangement.repository;

import edu.mangement.entity.BranchFeePerMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:46 AM
 */
@Repository
public interface BranchFeePerMonthRepository extends JpaRepository<BranchFeePerMonth,Long> {
}
