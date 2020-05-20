package edu.mangement.service;

import edu.mangement.model.MenuDTO;
import edu.mangement.model.MemberDTO;
import lombok.Getter;
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
}
