package edu.mangement.repository;

import edu.mangement.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 10:54 AM
 */
@Repository
public interface ItemsRepository extends JpaRepository<Items,Long> {
}
