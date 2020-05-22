package edu.mangement.repository;

import edu.mangement.entity.ProductInStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:59 AM
 */
@Repository
public interface ProductInStockRepository extends JpaRepository<ProductInStock,Long> {
}
