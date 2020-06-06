package edu.mangement.service;

import edu.mangement.entity.sp.TopProductSell;
import edu.mangement.model.OrderDetailDTO;
import edu.mangement.model.Paging;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/29/2020
 * TIME : 10:37 PM
 */
@Service
public interface OrderDetailService {
    List<OrderDetailDTO> findOrderDetailByOrderId(Long orderId, Paging paging,Pageable pageable);
    List<TopProductSell> getTopProductSell(Date date,Paging paging);
}
