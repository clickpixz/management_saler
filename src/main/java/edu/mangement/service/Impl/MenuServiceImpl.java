package edu.mangement.service.Impl;

import edu.mangement.entity.Auth;
import edu.mangement.entity.Menu;
import edu.mangement.entity.Role;
import edu.mangement.mapper.MenuMapper;
import edu.mangement.model.AuthForm;
import edu.mangement.model.MenuDTO;
import edu.mangement.model.RoleDTO;
import edu.mangement.repository.AuthRepository;
import edu.mangement.repository.MenuRepository;
import edu.mangement.service.MenuService;
import edu.mangement.service.RoleService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;
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
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthRepository authRepository;

    @Override
    public List<MenuDTO> findMenuUse(Long roleId) {
        return Optional.ofNullable(menuRepository.findMenuUse(roleId))
                .map(menus -> menus.stream()
                        .map(MenuMapper::toDTO)
                        .collect(Collectors.toList()))
                .orElseGet(null);
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
                if (childMenu.getParentId().equals(parentMenu.getId())) {
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

    @Override
    public Pair<Integer, List<MenuDTO>> findAllMapMenu(Pageable pageable) {
        Page<Menu> menuPage = menuRepository.findAll(pageable);
        Integer totalPages = menuPage.getTotalPages();
        var menuList = menuPage.getContent();
        var roleDTOList = roleService.findAllRole(null);
        List<MenuDTO> menuDTOList = new ArrayList<>();
        menuList.forEach(menu -> {
            // convert to tree map role
            var mapAuth = roleDTOList.stream()
                    .collect(Collectors.toMap(RoleDTO::getId, roleDTO -> 0, (oldValue, newValue) -> newValue, TreeMap::new));
            //map permision value each menu each role
            menu.getAuths().forEach(auth -> {
                mapAuth.put(auth.getRole().getId(), auth.getPermission());
                var menuDTO = MenuMapper.toDTO(menu);
                menuDTO.setMapAuth(mapAuth);
                menuDTOList.add(menuDTO);
            });
        });
        return new Pair<>(totalPages, menuDTOList);
    }

    @Override
    public MenuDTO findMenuById(Long id) {
        return Optional.ofNullable(id)
                .map(menuId -> menuRepository.findById(menuId).get())
                .map(MenuMapper::toDTO)
                .orElseGet(null);
    }

    @Override
    public void changeMenuStatus(MenuDTO menuDTO) throws Exception {
        if (menuDTO.getActiveFlag() == 1) {
            menuDTO.setActiveFlag(0);
        } else {
            menuDTO.setActiveFlag(1);
        }
        menuRepository.save(MenuMapper.toEntity(menuDTO));
    }

    @Override
    public List<MenuDTO> findAllMenu() {
        return menuRepository.findAll()
                .stream()
                .map(MenuMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void updatePermission(AuthForm authForm) throws Exception {
        var auth = authRepository.findByMenu_IdAndRole_Id(authForm.getMenuId(), authForm.getRoleId());
        if (auth != null) {
            auth.setPermission(authForm.getPermission());
            auth.setActiveFlag(1);
            authRepository.save(auth);
        }else {
            if (authForm.getPermission()==1) {
                Auth build = Auth.builder()
                        .activeFlag(1)
                        .permission(authForm.getPermission())
                        .menu(Menu.builder().id(authForm.getMenuId()).build())
                        .role(Role.builder().id(authForm.getRoleId()).build())
                        .build();
                authRepository.save(build);
            }
        }
    }
}
