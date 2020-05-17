package edu.mangement.dto.mapper;

import edu.mangement.dto.MemberDTO;
import edu.mangement.entity.Member;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 1:48 AM
 */
@Component
public class MemberMapper implements ModelMapper<MemberDTO, Member>{
    @Override
    public MemberDTO toDTO(Member member) {
        return null;
    }
}
