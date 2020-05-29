package edu.mangement.service;

import edu.mangement.model.InvoiceDTO;
import edu.mangement.model.OrderFilterForm;
import edu.mangement.model.Paging;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/29/2020
 * TIME : 8:57 PM
 */
@Service
public interface InvoiceService {
    List<InvoiceDTO> search(OrderFilterForm orderFilterForm, Paging paging);
    InvoiceDTO getById(Long id);
}
