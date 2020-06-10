package edu.mangement.service.Impl;

import edu.mangement.entity.Product;
import edu.mangement.entity.sp.DayQuantityMapper;
import edu.mangement.mapper.ProductMapper;
import edu.mangement.model.Paging;
import edu.mangement.model.ProductDTO;
import edu.mangement.model.SearchForm;
import edu.mangement.repository.ProductRepository;
import edu.mangement.service.FullTextSearchEngine;
import edu.mangement.service.ProductService;
import edu.mangement.utils.DateFormatUtils3;
import edu.mangement.utils.FileProcessUtils;
import javafx.util.Pair;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/25/2020
 * TIME : 4:20 PM
 */
@Component
@Transactional(rollbackOn = Exception.class)
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private FullTextSearchEngine<Product> fullTextSearchEngine;
    @Override
    public ProductDTO findProductById(Long id) {
        if (id != null) {
            var product = productRepository.findProductByIdAndActiveFlag(id, 1);
            if (product != null) {
                return ProductMapper.toDTO(product);
            }
        }
        return null;
    }

    @Override
    public ProductDTO findProductByCode(String code) {
        if (code != null) {
            var product = productRepository.findProductByCodeAndActiveFlag(code, 1);
            if (product != null) {
                return ProductMapper.toDTO(product);
            }
        }
        return null;
    }

    @Override
    public Pair<Integer, List<ProductDTO>> findAllProduct(Pageable pageable) {
        var pageProduct = productRepository.findAllByActiveFlag(1, pageable);
        var totalPages = pageProduct.getTotalPages();
        var productDTOList = pageProduct.get().map(ProductMapper::toDTO).collect(Collectors.toList());
        return new Pair<>(totalPages, productDTOList);
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) throws Exception {
        if (productDTO != null) {
            if (!productDTO.getMultipartFile().isEmpty()) {
                var fileName = FileProcessUtils.processUploadFile(productDTO.getMultipartFile());
                productDTO.setImage(fileName);
            }
            return Optional.of(productDTO)
                    .map(ProductMapper::toEntity)
                    .map(product -> productRepository.save(product))
                    .map(ProductMapper::toDTO)
                    .orElseGet(null);
        }
        return null;
    }

    @Override
    public void deleteProduct(ProductDTO productDTO) throws Exception {
        productDTO.setActiveFlag(0);
        productRepository.save(ProductMapper.toEntity(productDTO));
    }

    @Override
    public List<ProductDTO> searchProduct(SearchForm searchForm, Paging paging) {
        FullTextQuery fullTextQuery = fullTextSearchEngine
                .getFullTextQuery(searchForm, paging, Product.class,
                        "name", "code", "material", "description");
        List<Product> resultList = fullTextQuery.getResultList();
        return resultList.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Long getTotalQuantityProductSell() {
        return Long.valueOf((Integer)entityManager.
                createNamedStoredProcedureQuery("Product.getTotalQuantityProductSell")
                .getSingleResult());
    }

    @Override
    public Map<String, Long> contProductSold(Date date, Long id) {
        List<DayQuantityMapper> resultList = entityManager.createNamedStoredProcedureQuery("Product.countProductSoldInYear")
                .setParameter("DATE_FROM", date)
                .setParameter("productId", id)
                .getResultList();
        Map<String, Long> map = new LinkedHashMap<>();
//        map.put("")
//        resultList.stream().collect()
        return null;
    }
}
