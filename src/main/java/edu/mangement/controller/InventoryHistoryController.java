package edu.mangement.controller;

import edu.mangement.constant.Constant;
import edu.mangement.model.InventoryHistoryDTO;
import edu.mangement.model.Paging;
import edu.mangement.model.ProductInStockDTO;
import edu.mangement.service.BranchService;
import edu.mangement.service.CategoryService;
import edu.mangement.service.InventoryHistoryService;
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
@RequestMapping("/admin/inventory-history")
public class InventoryHistoryController {
    @Autowired
    private InventoryHistoryService inventoryHistoryService;
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
        return "redirect:/admin/inventory-history/list/1";
    }

    @RequestMapping("/list/{page}")
    public String show(@ModelAttribute("searchForm") InventoryHistoryDTO inventoryHistoryDTO, Model model, @PathVariable("page") int page, HttpSession session) {
        Paging paging = Paging.builder().recordPerPage(2).indexPage(page).build();
        var inventoryHistoryDTOList = inventoryHistoryService.search(inventoryHistoryDTO,paging);
        if (paging.getIndexPage() < page && paging.getTotalPages() > 0) {
            return "redirect:/admin/inventory-history/list/1";
        }
        Constant.sessionProcessor(model, session);
        model.addAttribute("inventoryHistoryDTOList", inventoryHistoryDTOList);
        model.addAttribute("tittlePage", "Danh sách các hóa đơn nhập xuất kho");
        model.addAttribute("paging", paging);
        initSelectBox(model);
        return "inventory-history-list";
    }
    public void initSelectBox(Model model){
        var allBranch = branchService.findAllBranch(Pageable.unpaged());
        model.addAttribute("allBranch",allBranch);
    }
}
