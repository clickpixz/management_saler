package edu.mangement.service.Impl;

import edu.mangement.constant.Constant;
import edu.mangement.entity.InventoryHistory;
import edu.mangement.entity.ProductInStock;
import edu.mangement.mapper.InventoryHistoryMapper;
import edu.mangement.mapper.MemberMapper;
import edu.mangement.model.InventoryHistoryDTO;
import edu.mangement.model.MemberDTO;
import edu.mangement.model.Paging;
import edu.mangement.repository.InventoryHistoryRepository;
import edu.mangement.repository.ProductInStockRepository;
import edu.mangement.service.InventoryHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/27/2020
 * TIME : 11:49 PM
 */
@Component
@Transactional(rollbackOn = Exception.class)
public class InventoryHistoryServiceImpl implements InventoryHistoryService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private InventoryHistoryRepository inventoryHistoryRepository;
    @Autowired
    private ProductInStockRepository productInStockRepository;

    @Override
    public List<InventoryHistoryDTO> search(InventoryHistoryDTO inventoryHistoryDTO, Paging paging) {
        var queryStr = new StringBuilder("SELECT ih from InventoryHistory ih WHERE ih.activeFlag =:activeFlag ");
        var countQueryStr = new StringBuilder("SELECT count(*) from InventoryHistory ih WHERE ih.activeFlag =:activeFlag");
        Map<String, Object> mapParams = new HashMap<>();
        prepareQuery(inventoryHistoryDTO, queryStr, countQueryStr, mapParams);
        TypedQuery<InventoryHistory> query = entityManager.createQuery(queryStr.toString(), InventoryHistory.class);
        Query countQuery = entityManager.createQuery(countQueryStr.toString());
        prepareStatement(mapParams, query, countQuery);
        pagingProcessor(paging, query, countQuery);
        return query.getResultList().stream().map(InventoryHistoryMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public InventoryHistoryDTO findById(Long id) {
        return Optional.ofNullable(id)
                .map(ih -> inventoryHistoryRepository.findInventoryHistoryByIdAndActiveFlag(ih, 1))
                .map(InventoryHistoryMapper::toDTO)
                .orElse(null);
    }

    @Override
    public InventoryHistoryDTO editInventoryHistory(InventoryHistoryDTO inventoryHistoryDTO, HttpSession session) throws Exception {
        if (inventoryHistoryDTO.getId() != null) {
//            lay thong tin nguoi thao tac
            MemberDTO memberDTO = (MemberDTO) session.getAttribute(Constant.USER_INFO);
            var productInStockId = inventoryHistoryDTO.getProductInStock().getId();
            //hoa don cu trong kho
            var inventoryHistory = inventoryHistoryRepository.getOne(inventoryHistoryDTO.getId());
            inventoryHistory.setDescription(inventoryHistoryDTO.getDescription());
            inventoryHistory.setMember(MemberMapper.toEntity(memberDTO));
            //san pham duoc goi tren view
            ProductInStock productInStock = productInStockRepository.getOne(productInStockId);
            //kiem tra xem minh co doi san pham ko
            if (productInStock.getId() == inventoryHistory.getProductInStock().getId()) {
                //san pham nhap vao == voi san pham cu
//                => thay doi thong tin # chi viec sua lai so luong san pham nhap xuat
                //set product in stock
                calculateQuantity(inventoryHistoryDTO, inventoryHistory);
                inventoryHistory.setType(inventoryHistoryDTO.getType());
                inventoryHistory.setQuantity(inventoryHistoryDTO.getQuantity());
                var save = inventoryHistoryRepository.save(inventoryHistory);
                return InventoryHistoryMapper.toDTO(save);
            } else {
                return calculateForProductChange(inventoryHistoryDTO, inventoryHistory, productInStock);
            }
        } else {
            throw new Exception();
        }
    }

    private InventoryHistoryDTO calculateForProductChange(InventoryHistoryDTO inventoryHistoryDTO, InventoryHistory inventoryHistory, ProductInStock productInStock) {
        //neu ta doi san pham #
        //neu truoc do la hoa don them moi vao kho
//                => delete cai san pham trong kho do di
//                cap nhat lai san pham moi
        var p1 = inventoryHistory.getProductInStock();
        if(inventoryHistory.getQuantity() == inventoryHistory.getProductInStock().getQuantity()){
            p1.setActiveFlag(0);//remove
            if(inventoryHistoryDTO.getType() == 1 ){
                productInStock.setQuantity(productInStock.getQuantity() + inventoryHistory.getQuantity());
            }else if(inventoryHistoryDTO.getType() == 0 ){
                productInStock.setQuantity(productInStock.getQuantity() - inventoryHistory.getQuantity());
            }
            productInStockRepository.save(p1);
            inventoryHistory.setProductInStock(productInStock);
            var save = inventoryHistoryRepository.save(inventoryHistory);
            inventoryHistory.setType(inventoryHistoryDTO.getType());
            inventoryHistory.setQuantity(inventoryHistoryDTO.getQuantity());
            return InventoryHistoryMapper.toDTO(save);
        }else {
//                    khong phai them moi vao kho
//                    cap nhat lai so luong
//                    roll back lai sl cho sp cu
            if(inventoryHistory.getType() == 1){
                p1.setQuantity(p1.getQuantity() - inventoryHistory.getQuantity());
            }else {
                p1.setQuantity(p1.getQuantity()+ inventoryHistory.getQuantity());
            }
            productInStockRepository.save(p1);
            //cap nhat so luong cho sp moi
            if(inventoryHistoryDTO.getType() == 1){
                productInStock.setQuantity(productInStock.getQuantity() - inventoryHistoryDTO.getQuantity());
            }else {
                productInStock.setQuantity(productInStock.getQuantity()+ inventoryHistoryDTO.getQuantity());
            }
            inventoryHistory.setProductInStock(productInStock);
            var save = inventoryHistoryRepository.save(inventoryHistory);
            inventoryHistory.setType(inventoryHistoryDTO.getType());
            inventoryHistory.setQuantity(inventoryHistoryDTO.getQuantity());
            return InventoryHistoryMapper.toDTO(save);
        }
    }

    private void calculateQuantity(InventoryHistoryDTO inventoryHistoryDTO, InventoryHistory inventoryHistory) {
        //neu ban dau la nhap ve sau van la nhap
        if (inventoryHistory.getType() == 1 && inventoryHistoryDTO.getType() == 1) {
            var p1 = inventoryHistory.getProductInStock();
            var sl1 = p1.getQuantity() - inventoryHistory.getQuantity() + inventoryHistoryDTO.getQuantity();
            p1.setQuantity(sl1);
            inventoryHistory.setProductInStock(p1);
        } else if (inventoryHistory.getType() ==  1 && inventoryHistoryDTO.getType() == 0) {
//              neu ban dau la nhap hang nhung lai la xuat hang
            var p2 = inventoryHistory.getProductInStock();
            var sl2 = p2.getQuantity() - inventoryHistory.getQuantity() - inventoryHistoryDTO.getQuantity();
            p2.setQuantity(sl2);
            inventoryHistory.setProductInStock(p2);
        } else if(inventoryHistory.getType() == 0 && inventoryHistoryDTO.getType() == 0){
            var p3 = inventoryHistory.getProductInStock();
            var sl3 = p3.getQuantity() + inventoryHistory.getQuantity() - inventoryHistoryDTO.getQuantity();
            p3.setQuantity(sl3);
            inventoryHistory.setProductInStock(p3);
        } else if(inventoryHistory.getType() == 0 && inventoryHistoryDTO.getType() == 1){
            var p4 = inventoryHistory.getProductInStock();
            var sl4 = p4.getQuantity() + inventoryHistory.getQuantity() + inventoryHistoryDTO.getQuantity();
            p4.setQuantity(sl4);
            inventoryHistory.setProductInStock(p4);
        }
    }

    private void prepareStatement(Map<String, Object> mapParams, TypedQuery<InventoryHistory> query, Query countQuery) {
        //set parameter
        if (mapParams != null && !mapParams.isEmpty()) {
            mapParams.forEach((k, v) -> {
                query.setParameter(k, v);
                countQuery.setParameter(k, v);
            });
        }
    }

    private void pagingProcessor(Paging paging, TypedQuery<InventoryHistory> query, Query countQuery) {
        query.setParameter("activeFlag", 1);
        countQuery.setParameter("activeFlag", 1);
        if (paging != null) {
            query.setFirstResult(paging.getOffset());
            query.setMaxResults(paging.getRecordPerPage());
            paging.setTotalRows((Long) countQuery.getSingleResult());
            paging.setTotalPages((int) Math.ceil(paging.getTotalRows() / (double) paging.getRecordPerPage()));
        }
    }

    private void prepareQuery(InventoryHistoryDTO inventoryHistoryDTO, StringBuilder queryStr, StringBuilder countQueryStr, Map<String, Object> mapParams) {
        var appendStr = new StringBuilder();
        //map params for search
        if (inventoryHistoryDTO != null) {
            if (inventoryHistoryDTO.getProductInStock() != null) {
                if (inventoryHistoryDTO.getProductInStock().getProduct() != null) {

                    if (!inventoryHistoryDTO.getProductInStock().getProduct().getCode().isBlank()) {
                        appendStr.append(" and ih.productInStock.product.code =:productCode ");
                        mapParams.put("productCode", inventoryHistoryDTO.getProductInStock().getProduct().getCode());
                    }
                }
                if (inventoryHistoryDTO.getProductInStock().getBranch() != null) {
                    if (inventoryHistoryDTO.getProductInStock().getBranch().getId() != null) {
                        appendStr.append(" and ih.productInStock.branch.id =:branchId ");
                        mapParams.put("branchId", inventoryHistoryDTO.getProductInStock().getBranch().getId());
                    }
                }
            }
            if (inventoryHistoryDTO.getCode() != null && !inventoryHistoryDTO.getCode().isBlank()) {
                appendStr.append(" and ih.code =:inventoryHistoryCode ");
                mapParams.put("inventoryHistoryCode", inventoryHistoryDTO.getCode());
            }
            if (inventoryHistoryDTO.getType() != null) {
                appendStr.append(" and ih.type =:type ");
                mapParams.put("type", inventoryHistoryDTO.getType());
            }
            //check from date and to date
            if (inventoryHistoryDTO.getFromDate() != null) {
                appendStr.append(" and ih.updateDate >= :fromDate");
                mapParams.put("fromDate", inventoryHistoryDTO.getFromDate());
            }
            if (inventoryHistoryDTO.getToDate() != null) {
                appendStr.append(" and ih.updateDate <= :toDate");
                mapParams.put("toDate", inventoryHistoryDTO.getToDate());
            }
        }
        queryStr.append(appendStr);
        countQueryStr.append(appendStr);
    }
}
