package edu.mangement.controller.activity;

import edu.mangement.constant.Constant;
import edu.mangement.model.FormInventory;
import edu.mangement.model.InventoryHistoryDTO;
import edu.mangement.model.Paging;
import edu.mangement.service.BranchService;
import edu.mangement.service.InventoryHistoryService;
import edu.mangement.validate.FormInventoryValidator;
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
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @Autowired
    private FormInventoryValidator formInventoryValidator;
    @InitBinder
    public void initBinder(WebDataBinder bind) {
        if (bind.getTarget() == null) {
            return;
        }
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        bind.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
//        if (bind.getTarget().getClass() == FormInventory.class) {
//            bind.setValidator(formInventoryValidator);
//        }
    }

    @RequestMapping(value = {"/list", "/list/"})
    public String redirectShow() {
        return "redirect:/admin/inventory-history/list/1";
    }

    @RequestMapping("/list/{page}")
    public String show(@ModelAttribute("searchForm") InventoryHistoryDTO inventoryHistoryDTO,
                       Model model, @PathVariable("page") int page, HttpSession session) {
        Paging paging = Paging.builder().recordPerPage(10).indexPage(page).build();
        System.out.println(inventoryHistoryDTO);
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
    @GetMapping("/view/{id}")
    public String view(Model model,@PathVariable("id") Long id){
        var inventoryHistoryDTO = inventoryHistoryService.findById(id);
        if (inventoryHistoryDTO != null) {
            model.addAttribute("tittlePage", "Chi tiết chi đơn nhập xuất kho");
            model.addAttribute("modelForm", inventoryHistoryDTO);
            model.addAttribute("viewOnly", true);
            return "inventory-history-action";
        } else {
            return "pages-404_alt";
        }
    }
    @GetMapping("/edit/{id}")
    public String edit(Model model,@PathVariable("id") Long id){
        var inventoryHistoryDTO = inventoryHistoryService.findById(id);
        if (inventoryHistoryDTO != null) {
            model.addAttribute("tittlePage", "Sửa đơn nhập xuất kho");
            model.addAttribute("modelForm", inventoryHistoryDTO);
            model.addAttribute("viewOnly", false);
            return "inventory-history-action";
        } else {
            return "pages-404_alt";
        }
    }
    @PostMapping("/save")
    public String save(HttpSession session,@ModelAttribute("modelForm") @Valid InventoryHistoryDTO inventoryHistoryDTO){
        try {
            inventoryHistoryService.editInventoryHistory(inventoryHistoryDTO,session);
            session.setAttribute(Constant.MSG_SUCCESS, "Edit success !!!");
        } catch (Exception e) {
            session.setAttribute(Constant.MSG_ERROR, "Process Has ERROR !!!");
            e.printStackTrace();
        }
        return "redirect:/admin/inventory-history/list";
    }
    public void initSelectBox(Model model){
        var allBranch = branchService.findAllBranch(Pageable.unpaged());
        model.addAttribute("allBranch",allBranch);
    }
    @GetMapping("/import")
    public String showForm(Model model){
        model.addAttribute("formInventory",new FormInventory());
        model.addAttribute("tittlePage","Nhập xuất kho");
        return "form-import-export";
    }
    @PostMapping("/import/save")
    public String saveImport(Model model , HttpSession session,
                             @ModelAttribute("formInventory") @Valid FormInventory formInventory,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("formInventory",formInventory);
            model.addAttribute("tittlePage","Nhập xuất kho");
            return "form-import-export";
        }
        try {
            inventoryHistoryService.importProduct(formInventory,session);
            session.setAttribute(Constant.MSG_SUCCESS, "Edit success !!!");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute(Constant.MSG_ERROR, e.getMessage());
        }
        return "redirect:/admin/inventory-history/list";
    }
}
