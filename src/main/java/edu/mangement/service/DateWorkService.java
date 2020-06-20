package edu.mangement.service;

import edu.mangement.model.BranchFeePerMonthDTO;
import edu.mangement.model.DateWorkDTO;
import edu.mangement.model.Paging;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DateWorkService {
    List<DateWorkDTO> search(DateWorkDTO dateWorkDTO, Paging paging);
    DateWorkDTO findDateWorkById(Long id);
    void deleteDateWork(DateWorkDTO dateWorkDTO) throws Exception;
    DateWorkDTO saveDateWork(DateWorkDTO dateWorkDTO) throws Exception;
}
