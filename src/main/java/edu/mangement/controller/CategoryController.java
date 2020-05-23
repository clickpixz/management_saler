package edu.mangement.controller;

import edu.mangement.constant.Constant;
import edu.mangement.model.CategoryDTO;
import edu.mangement.service.CategoryService;
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
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/20/2020
 * TIME : 2:43 PM
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

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
        return "redirect:/admin/category/list/1";
    }

    @GetMapping("/list/{page}")
    public String showCategoryList(Model model, @PathVariable("page") int page, HttpSession session) {
        List<CategoryDTO> categoryDTOList = categoryService.findAllCategory(PageRequest.of(page - 1, 5));
        Constant.sessionProcessor(model, session);
        model.addAttribute("categoryDTOList", categoryDTOList);
        model.addAttribute("tittlePage","Danh sách các Category");
        model.addAttribute("nameAddButton","Thêm Category");
        return "category-list";
    }

    @GetMapping("/add")
    public String addBranch(Model model) {
        model.addAttribute("tittlePage", "Thêm Category");
        model.addAttribute("modelForm", new CategoryDTO());
        model.addAttribute("viewOnly", false);
        return "category-action";
    }

    @GetMapping("/view/{id}")
    public String viewBranch(Model model, @PathVariable("id") Long id) {
        CategoryDTO categoryDTO = categoryService.findCategoryById(id);
        if (categoryDTO != null) {
            model.addAttribute("tittlePage", "Chi tiết Categgory");
            model.addAttribute("modelForm", categoryDTO);
            model.addAttribute("viewOnly", true);
            return "category-action";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/edit/{id}")
    public String editBranch(Model model, @PathVariable("id") Long id) {
        CategoryDTO categoryDTO = categoryService.findCategoryById(id);
        if (categoryDTO != null) {
            model.addAttribute("tittlePage", "Sửa Category");
            model.addAttribute("modelForm", categoryDTO);
            model.addAttribute("viewOnly", false);
            return "category-action";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBranch(Model model, @PathVariable("id") Long id, HttpSession session) {
        CategoryDTO categoryDTO = categoryService.findCategoryById(id);
        if (categoryDTO != null) {
            try {
                categoryService.deleteCategory(categoryDTO);
                session.setAttribute(Constant.MSG_SUCCESS, "Delete Success !!!");
            } catch (Exception e) {
                session.setAttribute(Constant.MSG_ERROR, "Delete has Error !!!");
                e.printStackTrace();
            }
            return "redirect:/admin/category/list";
        } else {
            return "pages-404_alt";
        }
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute(value = "modelForm") @Valid CategoryDTO categoryDTO,
                        BindingResult bindingResult,HttpSession session) {
        if (bindingResult.hasErrors()) {
            if (categoryDTO.getId() != null) {
                model.addAttribute("tittlePage", "Edit Category");
            } else {
                model.addAttribute("tittlePage", "Add Category");
            }
            model.addAttribute("modelForm", categoryDTO);
            model.addAttribute("viewOnly", false);
            return "category-action";
        }
        var msg = "";
        try {
            categoryDTO.setActiveFlag(1);
            categoryService.saveCategory(categoryDTO);
            if (categoryDTO.getId() != null) {
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
        return "redirect:/admin/category/list";
    }
}
