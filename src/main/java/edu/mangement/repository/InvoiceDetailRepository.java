package edu.mangement.repository;

import edu.mangement.entity.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:53 AM
 */
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail,Long> {
}
