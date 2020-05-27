package edu.mangement.service;

import edu.mangement.model.MemberDTO;
import edu.mangement.security.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/17/2020
 * TIME : 6:47 PM
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private MemberService memberService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO memberDTO = memberService.findByUsername(username);
        if(memberDTO!=null){
            return new CustomUserDetail(memberDTO);
        }else {
            throw new UsernameNotFoundException(username);
        }
    }
}
