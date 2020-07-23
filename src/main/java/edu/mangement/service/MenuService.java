package edu.mangement.service;

import edu.mangement.model.AuthForm;
import edu.mangement.model.MenuDTO;
import edu.mangement.model.Paging;
import org.springframework.data.domain.Pageable;
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
    List<MenuDTO> findAllMapMenu(Pageable pageable, Paging paging);
    MenuDTO findMenuById(Long id);
    void changeMenuStatus(MenuDTO menuDTO) throws Exception;
    List<MenuDTO> findAllMenu();
    void updatePermission(AuthForm authForm) throws Exception;
}
