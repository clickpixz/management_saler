package edu.mangement.repository;

import edu.mangement.entity.InventoryHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:51 AM
 */
@Repository
public interface InventoryHistoryRepository extends JpaRepository<InventoryHistory,Long> {
    Page<InventoryHistory> findAllByActiveFlag(int activeFlag, Pageable pageable);
    InventoryHistory findInventoryHistoryByActiveFlag(int activeFlag);
    InventoryHistory findInventoryHistoryByCodeAndActiveFlag(String code,int activeFlag);
}
