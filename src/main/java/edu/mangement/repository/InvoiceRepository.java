package edu.mangement.repository;

import edu.mangement.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:52 AM
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
    Invoice getInvoiceByIdAndActiveFlag(Long id,int activeFlag);
}
