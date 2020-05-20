package edu.mangement.service.Impl;

import edu.mangement.entity.Member;
import edu.mangement.entity.Menu;
import edu.mangement.entity.Role;
import edu.mangement.mapper.MemberMapper;
import edu.mangement.model.MenuDTO;
import edu.mangement.model.MemberDTO;
import edu.mangement.repository.MemberRepository;
import edu.mangement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/17/2020
 * TIME : 11:43 PM
 */
@Transactional
@Component
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MemberDTO findByUsername(String username) {
        return memberRepository
                .findByUsernameAndActiveFlag(username,1).map(MemberMapper::toDTO)
                .orElse(null);
    }
}
