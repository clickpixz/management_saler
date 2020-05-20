package edu.mangement.service;

import edu.mangement.model.MenuDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/19/2020
 * TIME : 1:11 PM
 */
@Service
public interface MenuService {
    List<MenuDTO> findMenuUse(Long roleId);
    List<MenuDTO> generateMenu(Long roleId);
}
