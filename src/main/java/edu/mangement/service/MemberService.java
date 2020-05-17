package edu.mangement.service;

import edu.mangement.entity.Member;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/17/2020
 * TIME : 11:43 PM
 */
@Service
public interface MemberService {
    Member findByUsername(String username);
}
