package edu.mangement.controller.activity;

import edu.mangement.constant.Constant;
import edu.mangement.model.BranchDTO;
import edu.mangement.model.Paging;
import edu.mangement.model.ProductDTO;
import edu.mangement.model.ProductInStockDTO;
import edu.mangement.service.BranchService;
import edu.mangement.service.CategoryService;
import edu.mangement.service.ProductInStockService;
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
        Paging paging = Paging.builder().recordPerPage(10).indexPage(page).build();
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
    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") Long id) {
        var productInStockDTO = productInStockService.findById(id);
        if (productInStockDTO != null) {
            productInStockDTO.setProductId(productInStockDTO.getProductId());
            productInStockDTO.setBranchId(productInStockDTO.getBranch().getId());
            model.addAttribute("tittlePage", "Chi tiết sản phẩm trong kho");
            model.addAttribute("viewOnly", true);
            model.addAttribute("modelForm", productInStockDTO);
            return "product-in-stock-action";
        }
        return "pages-404_alt";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        var productInStockDTO = productInStockService.findById(id);
        if (productInStockDTO != null) {
            productInStockDTO.setProductId(productInStockDTO.getProduct().getId());
            productInStockDTO.setBranchId(productInStockDTO.getBranch().getId());
            model.addAttribute("tittlePage", "Sửa sản phẩm trong kho");
            model.addAttribute("viewOnly", false);
            model.addAttribute("modelForm", productInStockDTO);
            return "product-in-stock-action";
        }
        return "pages-404_alt";
    }

    @GetMapping("/delete/{id}")
    public String delete(HttpSession session, @PathVariable("id") Long id) {
        var productInStockDTO = productInStockService.findById(id);
        if (productInStockDTO != null) {
            try {
                productInStockDTO.setActiveFlag(0);
                productInStockService.saveProduct(productInStockDTO);
                session.setAttribute(Constant.MSG_SUCCESS, "Delete Success !!!");
            } catch (Exception e) {
                session.setAttribute(Constant.MSG_ERROR, "Delete has Error !!!");
                e.printStackTrace();
            }
            return "redirect:/admin/product-in-stock/list/1";
        }
        return "pages-404_alt";
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute(value = "modelForm") @Valid ProductInStockDTO productInStockDTO,
                       BindingResult bindingResult, HttpSession session) {
        System.out.println(productInStockDTO);
        if (bindingResult.hasErrors()) {
            model.addAttribute("tittlePage", "Edit Product In Stock");
            model.addAttribute("modelForm", productInStockDTO);
            model.addAttribute("viewOnly", false);
            return "product-in-stock-action";
        }
        try {
            productInStockDTO.setActiveFlag(1);
            var branch = BranchDTO.builder().id(productInStockDTO.getBranchId()).build();
            var product = ProductDTO.builder().id(productInStockDTO.getProductId()).build();
            productInStockDTO.setBranch(branch);
            productInStockDTO.setProduct(product);
            productInStockService.saveProduct(productInStockDTO);
            session.setAttribute(Constant.MSG_SUCCESS, "Save success  !!!");
        } catch (Exception e) {
            session.setAttribute(Constant.MSG_ERROR, "Process Has ERROR !!!");
            e.printStackTrace();
        }
        return "redirect:/admin/product-in-stock/list/1";
    }
    @GetMapping("/invoke/{id}")
    private String invokeProduct(@PathVariable("id") Long id,Model model,HttpSession session){
        ProductInStockDTO productInStockDTO = productInStockService.findById(id);
        if(productInStockDTO==null){
            session.setAttribute(Constant.MSG_ERROR, "Process Has ERROR !!!");
            return "redirect:/admin/product-in-stock/list/1";
        }
        model.addAttribute("tittlePage","Đăng bán sản phẩm");
        model.addAttribute("productInStockDTO",productInStockDTO);
        return "invoke-product-action";
    }
}
