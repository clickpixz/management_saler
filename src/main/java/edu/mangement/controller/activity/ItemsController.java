package edu.mangement.controller.activity;

import edu.mangement.constant.Constant;
import edu.mangement.entity.ProductInStock;
import edu.mangement.model.ItemsDTO;
import edu.mangement.model.Paging;
import edu.mangement.model.ProductInStockDTO;
import edu.mangement.service.BranchService;
import edu.mangement.service.CategoryService;
import edu.mangement.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
        Paging paging = Paging.builder().recordPerPage(10).indexPage(page).build();
        var itemsDTOList = itemsService.search(itemsDTO, paging);
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

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") Long id) {
        var items = itemsService.findById(id);
        if (items != null) {
            items.setProductInStockId(items.getProductInStock().getId());
            model.addAttribute("tittlePage", "Chi tiết sản phẩm đăng bán");
            model.addAttribute("viewOnly", true);
            model.addAttribute("modelForm", items);
            return "items-action";
        }
        return "pages-404_alt";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        var items = itemsService.findById(id);
        if (items != null) {
            items.setProductInStockId(items.getProductInStock().getId());
            model.addAttribute("tittlePage", "Sửa sản phẩm đăng bán");
            model.addAttribute("viewOnly", false);
            model.addAttribute("modelForm", items);
            return "items-action";
        }
        return "pages-404_alt";
    }

    @GetMapping("/delete/{id}")
    public String delete(HttpSession session, @PathVariable("id") Long id) {
        var items = itemsService.findById(id);
        if (items != null) {
            try {
                items.setActiveFlag(0);
                itemsService.saveItems(items);
                session.setAttribute(Constant.MSG_SUCCESS, "Delete Success !!!");
            } catch (Exception e) {
                session.setAttribute(Constant.MSG_ERROR, "Delete has Error !!!");
                e.printStackTrace();
            }
            return "redirect:/admin/items/list/1";
        }
        return "pages-404_alt";
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute(value = "modelForm") @Valid ItemsDTO itemsDTO,
                       BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tittlePage", "Edit Product In Stock");
            model.addAttribute("modelForm", itemsDTO);
            model.addAttribute("viewOnly", false);
            return "items-action";
        }
        try {
            var productInStockDTO = ProductInStockDTO.builder().id(itemsDTO.getProductInStockId()).build();
            itemsDTO.setActiveFlag(1);
            itemsDTO.setProductInStock(productInStockDTO);
            itemsService.saveItems(itemsDTO);
            session.setAttribute(Constant.MSG_SUCCESS, "Save success  !!!");
        } catch (Exception e) {
            session.setAttribute(Constant.MSG_ERROR, "Process Has ERROR !!!");
            e.printStackTrace();
        }
        return "redirect:/admin/items/list";
    }

    public void initSelectBox(Model model) {
        var allCategory = categoryService.findAllCategory(Pageable.unpaged());
        var allBranch = branchService.findAllBranch(Pageable.unpaged());
        model.addAttribute("allCategory", allCategory);
        model.addAttribute("allBranch", allBranch);
    }
}
