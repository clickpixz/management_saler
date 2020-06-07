package edu.mangement.service;

import edu.mangement.model.OrderDTO;
import edu.mangement.model.OrderFilterForm;
import edu.mangement.model.Paging;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/28/2020
 * TIME : 4:30 PM
 */
@Service
public interface OrderService {
    List<OrderDTO> search(OrderFilterForm orderFilterForm, Paging paging);
    Map<String,Long> countOrderByDay(Date date);
}
