package edu.mangement.controller;

import edu.mangement.constant.Constant;
import edu.mangement.model.AuthForm;
import edu.mangement.model.MenuDTO;
import edu.mangement.model.Paging;
import edu.mangement.model.RoleDTO;
import edu.mangement.service.MenuService;
import edu.mangement.service.RoleService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/20/2020
 * TIME : 2:43 PM
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @InitBinder
    public void initBinder(WebDataBinder bind) {
        if (bind.getTarget() == null) {
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bind.registerCustomEditor(Data.class, new CustomDateEditor(simpleDateFormat, false));
    }

    @RequestMapping(value = {"/list", "/list/"})
    public String redirectShow() {
        return "redirect:/admin/menu/list/1";
    }

    @GetMapping("/list/{page}")
    public String showCategoryList(Model model, @PathVariable("page") Integer page, HttpSession session) {
        Pair<Integer, List<MenuDTO>> listPair = menuService.findAllMapMenu(PageRequest.of(page - 1, 20, Sort.by("parentId")));
        Integer totalPages = listPair.getKey();
        if (totalPages < page) {
            return "redirect:/admin/menu/list/1";
        }
        Paging paging = Paging.builder().recordPerPage(20).indexPage(page).totalPages(totalPages).build();
        var menuDTOList = listPair.getValue();
        var roleDTOList = roleService.findAllRole();
        Constant.sessionProcessor(model, session);
        model.addAttribute("menuDTOList", menuDTOList);
        model.addAttribute("roleDTOList", roleDTOList);
        model.addAttribute("tittlePage", "Danh sách các menu");
        model.addAttribute("nameAddButton", "Thay đổi Quyền Truy nhập");
        model.addAttribute("paging", paging);
        return "menu-list";
    }

    @GetMapping("/change-status/{id}")
    public String deleteBranch(Model model, @PathVariable("id") Long id, HttpSession session) {
        var menuDTO = menuService.findMenuById(id);
        if (menuDTO != null) {
            try {
                menuService.changeMenuStatus(menuDTO);
                session.setAttribute(Constant.MSG_SUCCESS, "Change Status Success !!!");
            } catch (Exception e) {
                session.setAttribute(Constant.MSG_ERROR, "Change Status has Error !!!");
                e.printStackTrace();
            }
            return "redirect:/admin/menu/list";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/permission")
    public String addPermission(Model model) {
        model.addAttribute("tittlePage", "Thay đổi trạng thái permission");
        model.addAttribute("modelForm", new AuthForm());
        initSelectBox(model);
        return "menu-action";
    }

    @PostMapping("/update-permission")
    private String updatePermission(@ModelAttribute("modelForm") @Valid AuthForm authForm,
                                    Model model, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tittlePage", "Thay đổi trạng thái permission");
            model.addAttribute("modelForm", authForm);
            initSelectBox(model);
            return "menu-action";
        }
        var msg = "";
        try {
            menuService.updatePermission(authForm);
            msg = "Update Success !!!";
            session.setAttribute(Constant.MSG_SUCCESS, msg);
        } catch (Exception e) {
            session.setAttribute(Constant.MSG_ERROR, "Process Has ERROR !!!");
            e.printStackTrace();
        }
        return "redirect:/admin/menu/list";
    }

    private void initSelectBox(Model model) {
        var mapMenus = menuService.findAllMenu()
                .stream().collect(Collectors.toMap(
                        MenuDTO::getId,
                        menuDTO -> "Menu name : " + menuDTO.getName() + "  -  URL : " + menuDTO.getUrl(),
                        (o, n) -> n));
        var mapRoles = roleService.findAllRole()
                .stream()
                .collect(Collectors.toMap(RoleDTO::getId, RoleDTO::getName, (o, n) -> n));

        model.addAttribute("mapRoles", mapRoles);
        model.addAttribute("mapMenus", mapMenus);
    }
}
