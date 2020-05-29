package edu.mangement.repository;

import edu.mangement.entity.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:56 AM
 */
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    Page<OrderDetail> findOrderDetailByActiveFlagAndOrder_Id(int activeFlag, Long orderId, Pageable pageable);
}
