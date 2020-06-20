package edu.mangement.service.Impl;

import edu.mangement.entity.BranchFeePerMonth;
import edu.mangement.mapper.BranchFeePerMonthMapper;
import edu.mangement.model.BranchFeePerMonthDTO;
import edu.mangement.model.Paging;
import edu.mangement.repository.BranchFeePerMonthRepository;
import edu.mangement.service.BranchFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional(rollbackOn = Exception.class)
public class BranchFeeServiceImpl implements BranchFeeService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private BranchFeePerMonthRepository branchFeePerMonthRepository;

    @Override
    public List<BranchFeePerMonthDTO> search(BranchFeePerMonthDTO branchFeePerMonthDTO, Paging paging) {
        var queryStr = new StringBuilder("SELECT BF from BranchFeePerMonth BF WHERE BF.activeFlag =:activeFlag ");
        var countQueryStr = new StringBuilder("SELECT count(*) from BranchFeePerMonth BF WHERE BF.activeFlag =:activeFlag");
        Map<String, Object> mapParams = new HashMap<>();
        prepareQuery(branchFeePerMonthDTO, queryStr, countQueryStr, mapParams);
        TypedQuery<BranchFeePerMonth> query = entityManager.createQuery(queryStr.toString(), BranchFeePerMonth.class);
        Query countQuery = entityManager.createQuery(countQueryStr.toString());
        prepareStatement(mapParams, query, countQuery);
        pagingProcessor(paging, query, countQuery);
        return query.getResultList().stream().map(BranchFeePerMonthMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public BranchFeePerMonthDTO findBranchFeeById(Long id) {
        return Optional.ofNullable(id)
                .map(aLong -> branchFeePerMonthRepository.findByIdAndActiveFlag(aLong,1))
                .map(BranchFeePerMonthMapper::toDTO)
                .orElse(null);
    }

    @Override
    public void deleteBranchFee(BranchFeePerMonthDTO branchFeePerMonthDTO) throws Exception{
        branchFeePerMonthDTO.setActiveFlag(0);
        branchFeePerMonthRepository.save(BranchFeePerMonthMapper.toEntity(branchFeePerMonthDTO));
    }

    @Override
    public BranchFeePerMonthDTO saveBranchFee(BranchFeePerMonthDTO branchFeePerMonthDTO) throws Exception {
        return Optional.ofNullable(branchFeePerMonthDTO)
                .map(BranchFeePerMonthMapper::toEntity)
                .map(branchFeePerMonth -> branchFeePerMonthRepository.save(branchFeePerMonth))
                .map(BranchFeePerMonthMapper::toDTO)
                .orElse(null);
    }

    private void prepareStatement(Map<String, Object> mapParams, TypedQuery<BranchFeePerMonth> query, Query countQuery) {
        //set parameter
        if (mapParams != null && !mapParams.isEmpty()) {
            mapParams.forEach((k, v) -> {
                query.setParameter(k, v);
                countQuery.setParameter(k, v);
            });
        }
    }

    private void pagingProcessor(Paging paging, TypedQuery<BranchFeePerMonth> query, Query countQuery) {
        query.setParameter("activeFlag", 1);
        countQuery.setParameter("activeFlag", 1);
        if (paging != null) {
            query.setFirstResult(paging.getOffset());
            query.setMaxResults(paging.getRecordPerPage());
            paging.setTotalRows((Long) countQuery.getSingleResult());
            paging.setTotalPages((int) Math.ceil(paging.getTotalRows() / (double) paging.getRecordPerPage()));
        }
    }

    private void prepareQuery(BranchFeePerMonthDTO branchFeePerMonthDTO, StringBuilder queryStr, StringBuilder countQueryStr, Map<String, Object> mapParams) {
        var appendStr = new StringBuilder();
        //map params for search
        if (branchFeePerMonthDTO != null) {
            if (branchFeePerMonthDTO.getBranchId() != null) {
                appendStr.append(" and BF.branch.id =:branchId");
                mapParams.put("branchId", branchFeePerMonthDTO.getBranchId());
            }
            //check from date and to date
            if (branchFeePerMonthDTO.getFromDate() != null) {
                appendStr.append(" and BF.updateDate >= :fromDate");
                mapParams.put("fromDate", branchFeePerMonthDTO.getFromDate());
            }
            if (branchFeePerMonthDTO.getToDate() != null) {
                appendStr.append(" and BF.updateDate <= :toDate");
                mapParams.put("toDate", branchFeePerMonthDTO.getToDate());
            }
        }
        queryStr.append(appendStr);
        countQueryStr.append(appendStr);
    }
}
