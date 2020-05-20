package edu.mangement.service.Impl;

import edu.mangement.mapper.MenuMapper;
import edu.mangement.model.MenuDTO;
import edu.mangement.repository.MenuRepository;
import edu.mangement.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/19/2020
 * TIME : 1:12 PM
 */
@Component
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<MenuDTO> findMenuUse(Long roleId) {
        return Optional.ofNullable(menuRepository.findMenuUse(roleId))
                .map(menus -> menus.stream()
                        .map(MenuMapper::toDTO)
                        .collect(Collectors.toList()))
                .orElseGet(() -> null);
    }

    @Override
    public List<MenuDTO> generateMenu(Long roleId) {
        List<MenuDTO> menuDTOList = findMenuUse(roleId).stream()
                .filter(menuDTO -> menuDTO.getOrderIndex() != -1)
                .collect(Collectors.toList());
        List<MenuDTO> parentMenuList = new ArrayList<>();
        List<MenuDTO> childMenuList = new ArrayList<>();
        //generate menu id
        menuDTOList.forEach(menuDTO -> {
            if (menuDTO.getParentId() == 0) {
                menuDTO.setIdMenu(menuDTO.getUrl().replace("/", "") + "Id");
                parentMenuList.add(menuDTO);
            } else if (menuDTO.getParentId() != 0) {
                menuDTO.setIdMenu(menuDTO.getUrl().replace("/", "") + "Id");
                childMenuList.add(menuDTO);
            }
        });
        //them tung menu con vao cac menu cha
        parentMenuList.forEach(parentMenu -> {
            //danh sach menu con cua moi menu cha
            var childMenus = new ArrayList<MenuDTO>();
            childMenuList.forEach(childMenu -> {
                //so sanh neu id menu cha == parentId cua menu con thi la menu con
                if (childMenu.getParentId() == parentMenu.getId()) {
                    childMenus.add(childMenu);
                }
            });
            parentMenu.setChild(childMenus);
        });
        //sap xep lai cac menu cha
        parentMenuList.sort(Comparator.comparingInt(MenuDTO::getOrderIndex));
        //sap xep lai cac menu con trong cac menu cha
        parentMenuList.forEach(parentMenu -> parentMenu
                .getChild()
                .sort(Comparator.comparingInt(MenuDTO::getOrderIndex)));
        return parentMenuList;
    }
}
