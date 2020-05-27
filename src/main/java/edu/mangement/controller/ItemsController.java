package edu.mangement.controller;

import edu.mangement.constant.Constant;
import edu.mangement.model.ItemsDTO;
import edu.mangement.model.Paging;
import edu.mangement.model.ProductInStockDTO;
import edu.mangement.service.BranchService;
import edu.mangement.service.CategoryService;
import edu.mangement.service.ItemsService;
import edu.mangement.service.ProductInStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/20/2020
 * TIME : 2:43 PM
 */
@Controller
@RequestMapping("/admin/items")
public class ItemsController {
    @Autowired
    private ItemsService itemsService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BranchService branchService;
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
        return "redirect:/admin/items/list/1";
    }

    @RequestMapping("/list/{page}")
    public String show(@ModelAttribute("searchForm") ItemsDTO itemsDTO, Model model, @PathVariable("page") int page, HttpSession session) {
        Paging paging = Paging.builder().recordPerPage(2).indexPage(page).build();
        var itemsDTOList = itemsService.search(itemsDTO,paging);
        if (paging.getIndexPage() < page && paging.getTotalPages() > 0) {
            return "redirect:/admin/product-in-stock/list/1";
        }
        Constant.sessionProcessor(model, session);
        model.addAttribute("itemsDTOList", itemsDTOList);
        model.addAttribute("tittlePage", "Danh sách các Sản Phẩm đang được bán");
        model.addAttribute("paging", paging);
        initSelectBox(model);
        return "items-list";
    }
    public void initSelectBox(Model model){
        var allCategory = categoryService.findAllCategory(Pageable.unpaged());
        var allBranch = branchService.findAllBranch(Pageable.unpaged());
        model.addAttribute("allCategory",allCategory);
        model.addAttribute("allBranch",allBranch);
    }
}
