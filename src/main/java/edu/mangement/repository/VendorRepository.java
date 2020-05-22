package edu.mangement.repository;

import edu.mangement.entity.Category;
import edu.mangement.entity.Vendor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 11:00 AM
 */
@Repository
public interface VendorRepository extends JpaRepository<Vendor,Long> {
    List<Vendor> findAllByActiveFlag(int activeFlag, Pageable pageable);
    Vendor findCategoryByIdAndActiveFlag(Long id,int activeFlag);
}
