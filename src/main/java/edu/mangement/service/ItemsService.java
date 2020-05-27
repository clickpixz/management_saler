package edu.mangement.service;

import edu.mangement.model.ItemsDTO;
import edu.mangement.model.Paging;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/27/2020
 * TIME : 10:23 PM
 */
@Service
public interface ItemsService {
    List<ItemsDTO> search(ItemsDTO itemsDTO,Paging paging);
}
