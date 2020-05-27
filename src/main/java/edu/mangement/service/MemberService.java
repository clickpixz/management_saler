package edu.mangement.service;

import edu.mangement.model.MemberDTO;
import edu.mangement.model.Paging;
import edu.mangement.model.SearchForm;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/17/2020
 * TIME : 11:43 PM
 */
@Service
public interface MemberService {
    MemberDTO findByUsername(String username);
    MemberDTO findById(Long id);
    List<MemberDTO> findAll(Pageable pageable, Paging paging);
    MemberDTO saveMember(MemberDTO memberDTO) throws Exception;
    void deleteMember(MemberDTO memberDTO) throws Exception;
    List<MemberDTO> search(SearchForm searchForm,Paging paging);
}
