package edu.mangement.repository;

import edu.mangement.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:54 AM
 */
public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByUsernameAndActiveFlag(String username,int activeFlag);
}
