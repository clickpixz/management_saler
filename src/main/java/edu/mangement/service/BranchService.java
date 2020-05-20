package edu.mangement.service;

import edu.mangement.model.BranchDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/20/2020
 * TIME : 4:32 PM
 */
@Service
public interface BranchService {
    BranchDTO saveBranch(BranchDTO branchDTO) throws Exception;
    List<BranchDTO> findAllBranch(Pageable pageable);
    BranchDTO findBranchById(Long id);
}
