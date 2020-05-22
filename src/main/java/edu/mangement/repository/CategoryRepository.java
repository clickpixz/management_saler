package edu.mangement.repository;

import edu.mangement.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:47 AM
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByActiveFlag(int activeFlag, Pageable pageable);
    Category findCategoryByIdAndActiveFlag(Long id,int activeFlag);
}
