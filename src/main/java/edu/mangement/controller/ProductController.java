package edu.mangement.controller;

import edu.mangement.constant.Constant;
import edu.mangement.model.*;
import edu.mangement.service.CategoryService;
import edu.mangement.service.ProductService;
import edu.mangement.service.VendorService;
import edu.mangement.validate.ProductValidator;
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
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private VendorService vendorService;
    @Autowired
    private ProductValidator productValidator;

    @InitBinder
    public void initBinder(WebDataBinder bind) {
        if (bind.getTarget() == null) {
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bind.registerCustomEditor(Data.class, new CustomDateEditor(simpleDateFormat, false));
        if (bind.getTarget().getClass() == ProductDTO.class) {
            bind.setValidator(productValidator);
        }
    }

    @RequestMapping(value = {"/list", "/list/"})
    public String redirectShow() {
        return "redirect:/admin/product/list/1";
    }

    @RequestMapping("/list/{page}")
    public String show(@ModelAttribute("searchForm") SearchForm searchForm, Model model, @PathVariable("page") int page, HttpSession session) {
        Paging paging = Paging.builder().recordPerPage(2).indexPage(page).build();
        List<ProductDTO> productDTOList;
        if (searchForm != null && searchForm.getField() != null && !searchForm.getField().isBlank()) {
            productDTOList = productService.searchProduct(searchForm, paging);
        } else {
            var pairProduct = productService.findAllProduct(PageRequest.of(page - 1, 2));
            var totalPages = pairProduct.getKey();
            productDTOList = pairProduct.getValue();
            paging.setTotalPages(totalPages);
            if (totalPages < page&&totalPages>0) {
                return "redirect:/admin/product/list/1";
            }
        }
        Constant.sessionProcessor(model, session);
        model.addAttribute("productDTOList", productDTOList);
        model.addAttribute("tittlePage", "Danh sách các Sản Phẩm");
        model.addAttribute("nameAddButton", "Thêm Sản phẩm");
        model.addAttribute("paging",paging);
        return "product-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        initSelectBox(model);
        model.addAttribute("tittlePage", "Thêm Sản phẩm");
        model.addAttribute("modelForm", new ProductDTO());
        model.addAttribute("viewOnly", false);
        return "product-action";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") Long id) {
        var productDTO = productService.findProductById(id);
        if (productDTO != null) {
            productDTO.setCategoryId(productDTO.getCategory().getId());
            productDTO.setVendorId(productDTO.getVendor().getId());
            model.addAttribute("tittlePage", "Chi tiết Sản phẩm");
            model.addAttribute("modelForm", productDTO);
            model.addAttribute("viewOnly", true);
            return "product-action";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        var productDTO = productService.findProductById(id);
        if (productDTO != null) {
            productDTO.setCategoryId(productDTO.getCategory().getId());
            productDTO.setVendorId(productDTO.getVendor().getId());
            model.addAttribute("tittlePage", "Sửa Sản phẩm");
            model.addAttribute("modelForm", productDTO);
            model.addAttribute("viewOnly", false);
            initSelectBox(model);
            return "product-action";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, HttpSession session) {
        var productDTO = productService.findProductById(id);
        if (productDTO != null) {
            try {
                productService.deleteProduct(productDTO);
                session.setAttribute(Constant.MSG_SUCCESS, "Delete Success !!!");
            } catch (Exception e) {
                session.setAttribute(Constant.MSG_ERROR, "Delete has Error !!!");
                e.printStackTrace();
            }
            return "redirect:/admin/product/list";
        } else {
            return "pages-404_alt";
        }
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute(value = "modelForm") @Valid ProductDTO productDTO,
                       BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            if (productDTO.getId() != null) {
                model.addAttribute("tittlePage", "Edit Vendor");
            } else {
                model.addAttribute("tittlePage", "Add Vendor");
            }
            initSelectBox(model);
            model.addAttribute("modelForm", productDTO);
            model.addAttribute("viewOnly", false);
            return "product-action";
        }
        var msg = "";
        try {
            var vendorDTO = VendorDTO.builder().id(productDTO.getVendorId()).build();
            var categoryDTO = CategoryDTO.builder().id(productDTO.getCategoryId()).build();
            productDTO.setVendor(vendorDTO);
            productDTO.setCategory(categoryDTO);
            productDTO.setActiveFlag(1);
            productService.saveProduct(productDTO);
            if (productDTO.getId() != null) {
//            => method = update
                msg = "Save Vendor Success !!!";
            } else {
//                method = add
                msg = "Add Vendor Success !!!";
            }
            session.setAttribute(Constant.MSG_SUCCESS, msg);
        } catch (Exception e) {
            session.setAttribute(Constant.MSG_ERROR, "Process Has ERROR !!!");
            e.printStackTrace();
        }
        return "redirect:/admin/product/list";
    }

    private void initSelectBox(Model model) {
        Map<Long, String> allVendor = vendorService.findAllVendor(Pageable.unpaged())
                .stream().collect(Collectors.toMap(VendorDTO::getId, VendorDTO::getName, (o, n) -> n, HashMap::new));
        Map<Long, String> allCategory = categoryService.findAllCategory(Pageable.unpaged())
                .stream().collect(Collectors.toMap(CategoryDTO::getId, CategoryDTO::getName, (o, n) -> n, HashMap::new));
        model.addAttribute("mapVendor", allVendor);
        model.addAttribute("mapCategory", allCategory);
    }
}
