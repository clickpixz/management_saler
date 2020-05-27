package edu.mangement.service.Impl;

import edu.mangement.entity.Member;
import edu.mangement.mapper.MemberMapper;
import edu.mangement.model.MemberDTO;
import edu.mangement.model.Paging;
import edu.mangement.model.SearchForm;
import edu.mangement.repository.MemberRepository;
import edu.mangement.service.FullTextSearchEngine;
import edu.mangement.service.MemberService;
import edu.mangement.utils.FileProcessUtils;
import org.hibernate.search.jpa.FullTextQuery;
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
 * DATE : 5/17/2020
 * TIME : 11:43 PM
 */
@Component
@Transactional(rollbackOn = Exception.class)
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private FullTextSearchEngine<Member> fullTextSearchEngine;

    @Override
    public MemberDTO findByUsername(String username) {
        return memberRepository
                .findByUsernameAndActiveFlag(username, 1).map(MemberMapper::toDTO)
                .orElse(null);
    }

    @Override
    public MemberDTO findById(Long id) {
        return Optional.ofNullable(id)
                .map(memberId -> memberRepository.findMemberByIdAndActiveFlag(memberId, 1))
                .map(MemberMapper::toDTO)
                .orElse(null);
    }

    @Override
    public List<MemberDTO> findAll(Pageable pageable, Paging paging) {
        var memberList = memberRepository.findAllByActiveFlag(1, pageable);
        paging.setTotalPages(memberList.getTotalPages());
        paging.setTotalRows(memberList.getTotalElements());
        return memberList.getContent().stream().map(MemberMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public MemberDTO saveMember(MemberDTO memberDTO) throws Exception {
        if (memberDTO != null) {
            if (!memberDTO.getMultipartFile().isEmpty()) {
                var fileName = FileProcessUtils.processUploadFile(memberDTO.getMultipartFile());
                memberDTO.setImage(fileName);
            }
            return Optional.ofNullable(memberDTO)
                    .map(MemberMapper::toEntity)
                    .map(member -> memberRepository.save(member))
                    .map(MemberMapper::toDTO)
                    .orElse(null);
        }
        return null;
    }

    @Override
    public void deleteMember(MemberDTO memberDTO) throws Exception {
        memberDTO.setActiveFlag(0);
        memberRepository.save(MemberMapper.toEntity(memberDTO));
    }

    @Override
    public List<MemberDTO> search(SearchForm searchForm, Paging paging) {
        FullTextQuery fullTextQuery = fullTextSearchEngine
                .getFullTextQuery(searchForm, paging, Member.class,
                        "username", "name", "doB");
        List<Member> resultList = fullTextQuery.getResultList();
        return resultList.stream().map(MemberMapper::toDTO).collect(Collectors.toList());
    }


}
