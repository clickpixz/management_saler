package edu.mangement.service;

import edu.mangement.model.InventoryHistoryDTO;
import edu.mangement.model.Paging;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/27/2020
 * TIME : 11:49 PM
 */
@Service
public interface InventoryHistoryService {
    List<InventoryHistoryDTO> search(InventoryHistoryDTO inventoryHistoryDTO, Paging paging);
    InventoryHistoryDTO findById(Long id);
    InventoryHistoryDTO editInventoryHistory(InventoryHistoryDTO inventoryHistoryDTO, HttpSession session) throws Exception;
}
