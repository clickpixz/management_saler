package edu.mangement.repository;

import edu.mangement.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:56 AM
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
}
