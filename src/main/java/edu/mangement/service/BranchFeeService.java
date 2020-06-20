package edu.mangement.service;

import edu.mangement.model.BranchFeePerMonthDTO;
import edu.mangement.model.Paging;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BranchFeeService {
    List<BranchFeePerMonthDTO> search(BranchFeePerMonthDTO branchFeePerMonthDTO, Paging paging);
    BranchFeePerMonthDTO findBranchFeeById(Long id);
    void deleteBranchFee(BranchFeePerMonthDTO branchFeePerMonthDTO) throws Exception;
    BranchFeePerMonthDTO saveBranchFee(BranchFeePerMonthDTO branchFeePerMonthDTO) throws Exception;
}
