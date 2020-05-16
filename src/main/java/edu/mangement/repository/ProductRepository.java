package edu.mangement.repository;

import edu.mangement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:58 AM
 */
public interface ProductRepository extends JpaRepository<Product,Long> {
}
