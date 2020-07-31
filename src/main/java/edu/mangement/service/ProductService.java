package edu.mangement.service;

import edu.mangement.entity.sp.DayQuantityMapper;
import edu.mangement.model.Paging;
import edu.mangement.model.ProductDTO;
import edu.mangement.model.SearchForm;
import edu.mangement.model.form.api.AjaxResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/25/2020
 * TIME : 4:17 PM
 */
@Service
public interface ProductService {
    ProductDTO findProductById(Long id);
    ProductDTO findProductByCode(String code);
    List<ProductDTO> findAllProduct(Pageable pageable,Paging paging);
    ProductDTO saveProduct(ProductDTO productDTO) throws Exception;
    void deleteProduct(ProductDTO productDTO) throws Exception;
    List<ProductDTO> searchProduct(SearchForm searchForm, Paging paging);
    Long getTotalQuantityProductSell();
    List<DayQuantityMapper> contProductSold(Date date, Long id);
    List<AjaxResponse> calculateInterest(Long branchId);
}
