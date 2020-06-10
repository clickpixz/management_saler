package edu.mangement.service.Impl;

import edu.mangement.entity.Customer;
import edu.mangement.entity.sp.CustomerResult;
import edu.mangement.mapper.CustomerMapper;
import edu.mangement.model.CustomerDTO;
import edu.mangement.model.Paging;
import edu.mangement.model.SearchForm;
import edu.mangement.repository.CustomerRepository;
import edu.mangement.service.CustomerService;
import edu.mangement.service.FullTextSearchEngine;
import javafx.util.Pair;
import org.hibernate.search.jpa.FullTextQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/24/2020
 * TIME : 4:03 PM
 */
@Component
@Transactional(rollbackOn = Exception.class)
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FullTextSearchEngine<Customer> fullTextSearchEngine;
    @Autowired
    private EntityManager entityManager;

    @Override
    public CustomerDTO findByCustomerId(Long id) {
        return Optional.ofNullable(id)
                .map(customerId -> customerRepository.findCustomerByIdAndActiveFlag(customerId, 1))
                .map(CustomerMapper::toDTO)
                .orElseGet(null);
    }

    @Override
    public Pair<Integer, List<CustomerDTO>> findAll(Pageable pageable) {
        var pageCustomer = customerRepository.findAllByActiveFlag(1, pageable);
        var totalPages = pageCustomer.getTotalPages();
        var customerDTOList = pageCustomer.get().map(CustomerMapper::toDTO).collect(Collectors.toList());
        Pair<Integer, List<CustomerDTO>> pair = new Pair<>(totalPages, customerDTOList);
        return pair;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) throws Exception {
        return Optional.ofNullable(customerDTO)
                .map(CustomerMapper::toEntity)
                .map(customer -> customerRepository.save(customer))
                .map(CustomerMapper::toDTO)
                .orElseGet(null);
    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO) throws Exception {
        customerDTO.setActiveFlag(0);
        customerRepository.save(CustomerMapper.toEntity(customerDTO));
    }

    @Override
    public List<CustomerDTO> search(SearchForm searchForm, Paging paging) {
        FullTextQuery fullTextQuery = fullTextSearchEngine
                .getFullTextQuery(searchForm, paging, Customer.class,
                        "username", "name", "address", "email", "phone", "birthday");
        List<Customer> resultList = fullTextQuery.getResultList();
        return resultList.stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<CustomerResult> getNewCustomerByWeek(Date dateFrom) {
        return entityManager
                .createNamedStoredProcedureQuery("Customer.getNewUserByWeek")
                .setParameter("DATE_FROM", dateFrom).getResultList();
    }

    @Override
    public Map<String, Long> countCustomerRegisters30DaysLeft(Date date) {
        List<CustomerResult> resultList = entityManager
                .createNamedStoredProcedureQuery("Customer.countNewCustomer30DayLeft")
                .setParameter("DATE_FROM", new Date()).getResultList();
        LinkedHashMap<Long, Long> collect = resultList.stream()
                .collect(Collectors.toMap(CustomerResult::getDateFrom,
                        CustomerResult::getQuantity,
                        (o, n) -> n,
                        LinkedHashMap::new));
//        double average = resultList.stream().mapToLong(CustomerResult::getQuantity).average().orElse(0);
        Set<Long> longs = collect.keySet();
        LocalDate localDate = LocalDate.now();
        Map<String, Long> map = new LinkedHashMap<>();
        LongStream.range(0, 30).forEach(value -> {
            if (longs.contains(value)) {
                map.put(localDate.minusDays(value).toString(), collect.get(value));
            } else {
                map.put(localDate.minusDays(value).toString(), 0L);
            }
        });
       return map;
    }
}
