package edu.mangement.controller;

import edu.mangement.constant.Constant;
import edu.mangement.model.RoleDTO;
import edu.mangement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/20/2020
 * TIME : 2:43 PM
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {
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
        return "redirect:/admin/role/list/1";
    }

    @GetMapping("/list/{page}")
    public String showRoleList(Model model, @PathVariable("page") int page, HttpSession session) {
        var roleDTOList = roleService.findAllRole(PageRequest.of(page - 1, 5));
        Constant.sessionProcessor(model, session);
        model.addAttribute("roleDTOList", roleDTOList);
        model.addAttribute("tittlePage","Danh sách các Role");
        model.addAttribute("nameAddButton","Thêm Role");
        return "role-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("tittlePage", "Thêm Role");
        model.addAttribute("modelForm", new RoleDTO());
        model.addAttribute("viewOnly", false);
        return "role-action";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") Long id) {
        var roleDTO = roleService.findRoleById(id);
        if (roleDTO != null) {
            model.addAttribute("tittlePage", "Chi tiết Role");
            model.addAttribute("modelForm", roleDTO);
            model.addAttribute("viewOnly", true);
            return "role-action";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        var roleDTO = roleService.findRoleById(id);
        if (roleDTO != null) {
            model.addAttribute("tittlePage", "Sửa Role");
            model.addAttribute("modelForm", roleDTO);
            model.addAttribute("viewOnly", false);
            return "role-action";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id, HttpSession session) {
        var roleDTO = roleService.findRoleById(id);
        if (roleDTO != null) {
            try {
                roleService.deleteRole(roleDTO);
                session.setAttribute(Constant.MSG_SUCCESS, "Delete Success !!!");
            } catch (Exception e) {
                session.setAttribute(Constant.MSG_ERROR, "Delete has Error !!!");
                e.printStackTrace();
            }
            return "redirect:/admin/role/list";
        } else {
            return "pages-404_alt";
        }
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute(value = "modelForm") @Valid RoleDTO roleDTO,
                        BindingResult bindingResult,HttpSession session) {
        if (bindingResult.hasErrors()) {
            if (roleDTO.getId() != null) {
                model.addAttribute("tittlePage", "Edit Role");
            } else {
                model.addAttribute("tittlePage", "Add Role");
            }
            model.addAttribute("modelForm", roleDTO);
            model.addAttribute("viewOnly", false);
            return "role-action";
        }
        var msg = "";
        try {
            roleDTO.setActiveFlag(1);
            roleService.saveRole(roleDTO);
            if (roleDTO.getId() != null) {
//            => method = update
                msg = "Save Category Success !!!";
            } else {
//                method = add
                msg = "Add Category Success !!!";
            }
            session.setAttribute(Constant.MSG_SUCCESS, msg);
        } catch (Exception e) {
            session.setAttribute(Constant.MSG_ERROR, "Process Has ERROR !!!");
            e.printStackTrace();
        }
        return "redirect:/admin/role/list";
    }
}
