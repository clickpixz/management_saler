package edu.mangement.service;

import edu.mangement.model.CustomerDTO;
import edu.mangement.model.Paging;
import edu.mangement.model.SearchForm;
import javafx.util.Pair;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/24/2020
 * TIME : 3:45 PM
 */
@Service
public interface CustomerService {
    CustomerDTO findByCustomerId(Long id);
    Pair<Integer, List<CustomerDTO>> findAll(Pageable pageable);
    CustomerDTO saveCustomer(CustomerDTO customerDTO) throws Exception;
    void deleteCustomer(CustomerDTO customerDTO) throws Exception;
    List<CustomerDTO> search(SearchForm searchForm, Paging paging);
}
