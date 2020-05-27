package edu.mangement.controller;

import edu.mangement.constant.Constant;
import edu.mangement.model.*;
import edu.mangement.service.BranchService;
import edu.mangement.service.CategoryService;
import edu.mangement.service.ProductInStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/20/2020
 * TIME : 2:43 PM
 */
@Controller
@RequestMapping("/admin/product-in-stock")
public class ProductInStockController {
    @Autowired
    private ProductInStockService productInStockService;
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
        return "redirect:/admin/product-in-stock/list/1";
    }

    @RequestMapping("/list/{page}")
    public String show(@ModelAttribute("searchForm") ProductInStockDTO productInStockDTO, Model model, @PathVariable("page") int page, HttpSession session) {
        Paging paging = Paging.builder().recordPerPage(2).indexPage(page).build();
        var productInStockDTOList = productInStockService.search(productInStockDTO,paging);
        if (paging.getIndexPage() < page && paging.getTotalPages() > 0) {
            return "redirect:/admin/product-in-stock/list/1";
        }
        Constant.sessionProcessor(model, session);
        model.addAttribute("productInStockDTOList", productInStockDTOList);
        model.addAttribute("tittlePage", "Danh sách các Sản Phẩm trong kho");
        model.addAttribute("paging", paging);
        initSelectBox(model);
        return "product-in-stock-list";
    }
    public void initSelectBox(Model model){
        var allCategory = categoryService.findAllCategory(Pageable.unpaged());
        var allBranch = branchService.findAllBranch(Pageable.unpaged());
        model.addAttribute("allCategory",allCategory);
        model.addAttribute("allBranch",allBranch);
    }
}
