package edu.mangement.repository;

import edu.mangement.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:54 AM
 */
@Repository
public interface ItemsRepository extends JpaRepository<Items,Long> {
    List<Items> findItemsByProductInStock_Id(Long productInStockId);
    Items findItemsByIdAndActiveFlag(Long id,int activeFlag);
}
