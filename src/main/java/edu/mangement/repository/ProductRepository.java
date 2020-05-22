package edu.mangement.repository;

import edu.mangement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:58 AM
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
