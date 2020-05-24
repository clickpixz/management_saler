package edu.mangement.repository;

import edu.mangement.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:48 AM
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findCustomerByIdAndActiveFlag(Long id, int activeFlag);
    Page<Customer> findAllByActiveFlag(int activeFlag, Pageable pageable);
}
