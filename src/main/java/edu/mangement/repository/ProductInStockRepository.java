package edu.mangement.repository;

import edu.mangement.entity.ProductInStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:59 AM
 */
@Repository
public interface ProductInStockRepository extends JpaRepository<ProductInStock,Long> {
    Page<ProductInStock> findAllByActiveFlag(int activeFlag,Pageable pageable);
    ProductInStock checkProductInStockExits(String productCode, String size , Long branchId , BigDecimal price, int activeFlag);
    ProductInStock findProductInStockByProduct_CodeAndActiveFlag(String code,int activeFlag);
    ProductInStock findProductInStockByIdAndActiveFlag(Long id,int activeFlag);
}
