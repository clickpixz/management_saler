package edu.mangement.service.Impl;

import edu.mangement.entity.Customer;
import edu.mangement.mapper.CustomerMapper;
import edu.mangement.model.CustomerDTO;
import edu.mangement.model.Paging;
import edu.mangement.model.SearchForm;
import edu.mangement.repository.CustomerRepository;
import edu.mangement.service.CustomerService;
import javafx.util.Pair;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Customer.class).get();
        Query query = queryBuilder.keyword()
                .onFields("username", "name", "address", "email", "phone", "birthday")
                .matching(searchForm.getField()).createQuery();
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, Customer.class);
        if (paging != null) {
            //so ban ghi 1 page
            fullTextQuery.setMaxResults(paging.getRecordPerPage());
            //ban ghi dau tien
            fullTextQuery.setFirstResult(paging.getOffset());
            //tong so ban ghi
            paging.setTotalRows(fullTextQuery.getResultSize());
            paging.setTotalPages((int) Math.ceil(paging.getTotalRows() / (double) paging.getRecordPerPage()));
        }
        List<Customer> resultList = fullTextQuery.getResultList();
        return resultList.stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
    }
}
