package edu.mangement.service.Impl;

import edu.mangement.mapper.BranchMapper;
import edu.mangement.model.BranchDTO;
import edu.mangement.repository.BranchRepository;
import edu.mangement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/20/2020
 * TIME : 4:35 PM
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class BranchServiceImpl implements BranchService {
    @Autowired
    private BranchRepository branchRepository;
    @Override
    public BranchDTO saveBranch(BranchDTO branchDTO) throws Exception{
        return Optional.ofNullable(branchDTO)
                .map(BranchMapper::toEntity)
                .map(branch -> branchRepository.save(branch))
                .map(BranchMapper::toDTO)
                .orElseGet(null);
    }

    @Override
    public List<BranchDTO> findAllBranch(Pageable pageable) {
        return branchRepository.findAllByActiveFlag(1,pageable).stream()
                                                    .map(BranchMapper::toDTO)
                                                    .collect(Collectors.toList());
    }

    @Override
    public BranchDTO findBranchById(Long id) {
        return null;
    }
}
