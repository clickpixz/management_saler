package edu.mangement.service;

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
      return Optional.of(memberService.findByUsername(username))
              .map(memberDTO -> new CustomUserDetail(memberDTO))
              .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
