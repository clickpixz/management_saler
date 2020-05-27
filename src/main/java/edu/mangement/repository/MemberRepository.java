package edu.mangement.repository;

import edu.mangement.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:54 AM
 */
@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUsernameAndActiveFlag(String username,int activeFlag);
    Member findMemberByIdAndActiveFlag(Long id,int activeFlag);
    Page<Member> findAllByActiveFlag(int activeFlag, Pageable pageable);
}
