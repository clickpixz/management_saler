package edu.mangement.service;

import edu.mangement.entity.Member;
import edu.mangement.repository.MemberRepository;
import edu.mangement.security.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/17/2020
 * TIME : 6:47 PM
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsernameAndActiveFlag(username, 1);
        System.out.println(member);
        if(member==null){
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetail(member);
    }

}
