package edu.mangement.repository;

import edu.mangement.entity.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:53 AM
 */
@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail,Long> {
}
