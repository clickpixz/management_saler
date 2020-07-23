package edu.mangement.service.Impl;

import edu.mangement.entity.BranchFeePerMonth;
import edu.mangement.entity.Product;
import edu.mangement.entity.sp.CustomerResult;
import edu.mangement.entity.sp.DayQuantityMapper;
import edu.mangement.entity.sp.InterestMapper;
import edu.mangement.mapper.ProductMapper;
import edu.mangement.model.Paging;
import edu.mangement.model.ProductDTO;
import edu.mangement.model.SearchForm;
import edu.mangement.model.form.api.AjaxResponse;
import edu.mangement.repository.BranchRepository;
import edu.mangement.repository.ProductRepository;
import edu.mangement.service.FullTextSearchEngine;
import edu.mangement.service.ProductService;
import edu.mangement.utils.FileProcessUtils;
import org.hibernate.search.jpa.FullTextQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

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
    @Autowired
    private BranchRepository branchRepository;

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
    public List<ProductDTO> findAllProduct(Pageable pageable,Paging paging) {
        var pageProduct = productRepository.findAllByActiveFlag(1, pageable);
        paging.setTotalPages(pageProduct.getTotalPages());
        paging.setTotalRows(pageProduct.getTotalElements());
        return pageProduct.get().map(ProductMapper::toDTO).collect(Collectors.toList());
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
        return Long.valueOf((Integer) entityManager.
                createNamedStoredProcedureQuery("Product.getTotalQuantityProductSell")
                .getSingleResult());
    }

    @Override
    public List<DayQuantityMapper> contProductSold(Date date, Long id) {
        List<CustomerResult> resultList = entityManager
                .createNamedStoredProcedureQuery("Product.countProductSoldInYear")
                .setParameter("DATE_FROM", date)
                .setParameter("productId", id)
                .getResultList();
        List<CustomerResult> resultList2 = entityManager
                .createNamedStoredProcedureQuery("Product.countProductSoldInYear_2")
                .setParameter("DATE_FROM", date)
                .setParameter("productId", id)
                .getResultList();
        resultList.addAll(resultList2);
        var collect = resultList.stream().collect(Collectors.toMap(
                CustomerResult::getDateFrom, CustomerResult::getQuantity,
                (o, n) -> o + n, LinkedHashMap::new));
        var keys = collect.keySet();
        List<DayQuantityMapper> list = new ArrayList<>();
        var localDate = LocalDate.of(2020, 1, 1);
        LongStream.range(1, 13).forEach(value -> {
            DayQuantityMapper dayQuantityMapper;
            if (keys.contains(value)) {
                dayQuantityMapper = DayQuantityMapper.builder()
                        .day(localDate.plusMonths(value - 1).toString())
                        .quantity(collect.get(value)).build();
            } else {
                dayQuantityMapper = DayQuantityMapper.builder()
                        .day(localDate.plusMonths(value - 1).toString())
                        .quantity(0L).build();
            }
            list.add(dayQuantityMapper);
        });
        return list;
    }

    @Override
    public List<AjaxResponse> calculateInterest(Long branchId) {
        List<AjaxResponse> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            LocalDate localDate = LocalDate.of(2020, i, 1);
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            List<InterestMapper> interestSoildProduct = entityManager
                    .createNamedStoredProcedureQuery("calculateInterestByProductId")
                    .setParameter("DATE_FROM", date)
                    .setParameter("branchId", branchId)
                    .getResultList();
            //calculate fee branch
            var branchFeePerMonthList = branchRepository.getOne(branchId).getBranchFeePerMonths();
            var feePerMonth = BigDecimal.ZERO;
            for (BranchFeePerMonth branchFeePerMonth : branchFeePerMonthList) {
                int month = branchFeePerMonth.getMonth().intValue();
                if (month == i) {
                    feePerMonth = feePerMonth.add(new BigDecimal(String.valueOf(branchFeePerMonth.getCost())));
                }
            }
            //calculate fee member
            List<InterestMapper> listMemberFee = entityManager
                    .createNamedStoredProcedureQuery("CalculateMemberSalary")
                    .setParameter("branchId", branchId)
                    .setParameter("NAM", 2020)
                    .setParameter("THANG", i)
                    .getResultList();
            BigDecimal memberFee = BigDecimal.ZERO;
            if(listMemberFee!=null){
                if(listMemberFee.size()>0){
                    if(listMemberFee.get(0).getTotal()!=null){
                       memberFee = memberFee.add(listMemberFee.get(0).getTotal());
                    }
                }
            }
            var ajaxResponse = AjaxResponse.builder()
                    .message(localDate.toString())
                    .interestMapper(checkNullAndMapZeroValue(interestSoildProduct.get(0), memberFee, feePerMonth))
                    .build();
            list.add(ajaxResponse);
        }
        return list;
    }

    private InterestMapper checkNullAndMapZeroValue(InterestMapper interestMapper,
                                                    BigDecimal memberFee, BigDecimal branchFeePerMonth) {
        if (interestMapper.getCapital() == null) {
            interestMapper.setCapital(BigDecimal.ZERO);
        }
        if (interestMapper.getInterest() == null) {
            interestMapper.setInterest(BigDecimal.ZERO);
        }
        if (interestMapper.getTotal() == null) {
            interestMapper.setTotal(BigDecimal.ZERO);
        }
        interestMapper.setBranchFee(branchFeePerMonth);
        interestMapper.setMemberFee(memberFee);
        return interestMapper;
    }
}
