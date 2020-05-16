package edu.mangement.repository;

import edu.mangement.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:52 AM
 */
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}
