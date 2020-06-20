package edu.mangement.service.Impl;

import edu.mangement.entity.DateWork;
import edu.mangement.mapper.DateWorkMapper;
import edu.mangement.model.DateWorkDTO;
import edu.mangement.model.Paging;
import edu.mangement.repository.DateWorkRepository;
import edu.mangement.service.DateWorkService;
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
public class DateWorkServiceImpl implements DateWorkService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private DateWorkRepository dateWorkRepository;
    @Override
    public List<DateWorkDTO> search(DateWorkDTO dateWorkDTO, Paging paging) {
        var queryStr = new StringBuilder("SELECT DW from DateWork DW WHERE DW.activeFlag =:activeFlag ");
        var countQueryStr = new StringBuilder("SELECT count(*) from DateWork DW WHERE DW.activeFlag =:activeFlag");
        Map<String, Object> mapParams = new HashMap<>();
        prepareQuery(dateWorkDTO, queryStr, countQueryStr, mapParams);
        TypedQuery<DateWork> query = entityManager.createQuery(queryStr.toString(), DateWork.class);
        Query countQuery = entityManager.createQuery(countQueryStr.toString());
        prepareStatement(mapParams, query, countQuery);
        pagingProcessor(paging, query, countQuery);
        return query.getResultList().stream().map(DateWorkMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public DateWorkDTO findDateWorkById(Long id) {
        return Optional.ofNullable(id)
                .map(aLong -> dateWorkRepository.findByIdAndActiveFlag(aLong,1))
                .map(DateWorkMapper::toDTO)
                .orElse(null);
    }

    @Override
    public void deleteDateWork(DateWorkDTO dateWorkDTO) throws Exception{
        dateWorkDTO.setActiveFlag(0);
        dateWorkRepository.save(DateWorkMapper.toEntity(dateWorkDTO));
    }

    @Override
    public DateWorkDTO saveDateWork(DateWorkDTO dateWorkDTO) throws Exception {
        return Optional.ofNullable(dateWorkDTO)
                .map(DateWorkMapper::toEntity)
                .map(dateWork -> dateWorkRepository.save(dateWork))
                .map(dateWork -> dateWorkDTO)
                .orElse(null);
    }

    private void prepareStatement(Map<String, Object> mapParams, TypedQuery<DateWork> query, Query countQuery) {
        //set parameter
        if (mapParams != null && !mapParams.isEmpty()) {
            mapParams.forEach((k, v) -> {
                query.setParameter(k, v);
                countQuery.setParameter(k, v);
            });
        }
    }

    private void pagingProcessor(Paging paging, TypedQuery<DateWork> query, Query countQuery) {
        query.setParameter("activeFlag", 1);
        countQuery.setParameter("activeFlag", 1);
        if (paging != null) {
            query.setFirstResult(paging.getOffset());
            query.setMaxResults(paging.getRecordPerPage());
            paging.setTotalRows((Long) countQuery.getSingleResult());
            paging.setTotalPages((int) Math.ceil(paging.getTotalRows() / (double) paging.getRecordPerPage()));
        }
    }

    private void prepareQuery(DateWorkDTO dateWorkDTO, StringBuilder queryStr, StringBuilder countQueryStr, Map<String, Object> mapParams) {
        var appendStr = new StringBuilder();
        //map params for search
        if (dateWorkDTO != null) {
            if (dateWorkDTO.getBranchId() != null) {
                appendStr.append(" and DW.member.branch.id =:branchId");
                mapParams.put("branchId", dateWorkDTO.getBranchId());
            }
            //check from date and to date
            if (dateWorkDTO.getFromDate() != null) {
                appendStr.append(" and DW.updateDate >= :fromDate");
                mapParams.put("fromDate", dateWorkDTO.getFromDate());
            }
            if (dateWorkDTO.getToDate() != null) {
                appendStr.append(" and DW.updateDate <= :toDate");
                mapParams.put("toDate", dateWorkDTO.getToDate());
            }
        }
        queryStr.append(appendStr);
        countQueryStr.append(appendStr);
    }
}
