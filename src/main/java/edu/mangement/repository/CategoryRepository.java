package edu.mangement.repository;

import edu.mangement.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:47 AM
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
