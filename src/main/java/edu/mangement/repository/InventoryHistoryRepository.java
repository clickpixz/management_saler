package edu.mangement.repository;

import edu.mangement.entity.InventoryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:51 AM
 */
public interface InventoryHistoryRepository extends JpaRepository<InventoryHistory,Long> {
}
