package edu.mangement.repository;

import edu.mangement.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:55 AM
 */
public interface OrderRepository extends JpaRepository<Order,Long> {
}
