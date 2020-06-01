package edu.mangement.service;

import edu.mangement.model.FormPushProduct;
import edu.mangement.model.Paging;
import edu.mangement.model.ProductInStockDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/27/2020
 * TIME : 3:44 PM
 */
@Service
public interface ProductInStockService {
    List<ProductInStockDTO> findAll(Pageable pageable, Paging paging);
    ProductInStockDTO findById(Long id);
    ProductInStockDTO findByCode(String code);
    List<ProductInStockDTO> search(ProductInStockDTO productInStockDTO, Paging paging);
    void pushProduct(FormPushProduct formPushProduct) throws Exception;
    void saveProduct(ProductInStockDTO productInStockDTO) throws Exception;
}
