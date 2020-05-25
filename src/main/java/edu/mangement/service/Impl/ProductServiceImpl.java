package edu.mangement.service.Impl;

import edu.mangement.entity.Product;
import edu.mangement.mapper.ProductMapper;
import edu.mangement.model.Paging;
import edu.mangement.model.ProductDTO;
import edu.mangement.model.SearchForm;
import edu.mangement.repository.ProductRepository;
import edu.mangement.service.ProductService;
import edu.mangement.utils.FileProcessUtils;
import javafx.util.Pair;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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
        //check save or update
        if (productDTO.getId() != null) {
//            => update
            if (!productDTO.getMultipartFile().isEmpty()) {
                var fileName = FileProcessUtils.processUploadFile(productDTO.getMultipartFile());
                productDTO.setImage(fileName);
            }
        }
        return Optional.of(productDTO)
                .map(ProductMapper::toEntity)
                .map(product -> productRepository.save(product))
                .map(ProductMapper::toDTO)
                .orElseGet(null);
    }

    @Override
    public void deleteProduct(ProductDTO productDTO) throws Exception {
        productDTO.setActiveFlag(0);
        productRepository.save(ProductMapper.toEntity(productDTO));
    }

    @Override
    public List<ProductDTO> searchProduct(SearchForm searchForm, Paging paging) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Product.class).get();
        Query query = queryBuilder.keyword().onFields("name", "code", "material", "description")
                .matching(searchForm.getField()).createQuery();
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, Product.class);
        if (paging != null) {
            //so ban ghi 1 page
            fullTextQuery.setMaxResults(paging.getRecordPerPage());
            //ban ghi dau tien
            fullTextQuery.setFirstResult(paging.getOffset());
            //tong so ban ghi
            paging.setTotalRows(fullTextQuery.getResultSize());
            paging.setTotalPages((int) Math.ceil(paging.getTotalRows() / (double) paging.getRecordPerPage()));
        }
        List<Product> resultList = fullTextQuery.getResultList();
        return resultList.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }
}
