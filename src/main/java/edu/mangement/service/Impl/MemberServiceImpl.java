package edu.mangement.service.Impl;

import edu.mangement.entity.Member;
import edu.mangement.repository.MemberRepository;
import edu.mangement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

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
    public Member findByUsername(String username) {
        return memberRepository
                .findByUsernameAndActiveFlag(username,1)
                .orElse(null);
    }
}
