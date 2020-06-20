package edu.mangement.repository;

import edu.mangement.entity.DateWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:49 AM
 */
@Repository
public interface DateWorkRepository extends JpaRepository<DateWork,Long> {
    DateWork findByIdAndActiveFlag(Long id,int activeFlag);
}
