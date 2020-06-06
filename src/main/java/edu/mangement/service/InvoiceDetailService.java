package edu.mangement.service;

import edu.mangement.entity.sp.TopProductSell;
import edu.mangement.model.Paging;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 6/6/2020
 * TIME : 9:52 PM
 */
@Service
public interface InvoiceDetailService {
    List<TopProductSell> getTopProductSell(Date date, Paging paging);
}
