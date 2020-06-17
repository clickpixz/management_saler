package edu.mangement.controller.activity;

import edu.mangement.constant.Constant;
import edu.mangement.model.CustomerDTO;
import edu.mangement.model.Paging;
import edu.mangement.model.SearchForm;
import edu.mangement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/20/2020
 * TIME : 2:43 PM
 */
@Controller
@RequestMapping("/admin/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

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
        return "redirect:/admin/customer/list/1";
    }

    @RequestMapping("/list/{page}")
    public String show(@ModelAttribute("searchForm") SearchForm searchForm, Model model, @PathVariable("page") int page, HttpSession session) {
        Paging paging = Paging.builder().recordPerPage(5).indexPage(page).build();
        List<CustomerDTO> customerDTOList;
        if (searchForm != null && searchForm.getField() != null && !searchForm.getField().isBlank()) {
            customerDTOList = customerService.search(searchForm, paging);
        } else {
            var pairCustomer = customerService.findAll(PageRequest.of(page - 1, 10));
            var totalPages = pairCustomer.getKey();
            customerDTOList = pairCustomer.getValue();
            paging.setTotalPages(totalPages);
            if (totalPages < page) {
                return "redirect:/admin/customer/list/1";
            }
            Constant.sessionProcessor(model, session);
        }
        model.addAttribute("customerDTOList", customerDTOList);
        model.addAttribute("tittlePage", "Danh sách các Khách hàng");
        model.addAttribute("paging", paging);
        return "customer-list";
    }


    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") Long id) {
        var customerDTO = customerService.findByCustomerId(id);
        if (customerDTO != null) {
            model.addAttribute("tittlePage", "Chi tiết Khách hàng");
            model.addAttribute("modelForm", customerDTO);
            model.addAttribute("viewOnly", true);
            return "customer-action";
        } else {
            return "pages-404_alt";
        }
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, HttpSession session) {
        var customerDTO = customerService.findByCustomerId(id);
        if (customerDTO != null) {
            try {
                customerService.deleteCustomer(customerDTO);
                session.setAttribute(Constant.MSG_SUCCESS, "Delete Success !!!");
            } catch (Exception e) {
                session.setAttribute(Constant.MSG_ERROR, "Delete has Error !!!");
                e.printStackTrace();
            }
            return "redirect:/admin/customer/list";
        } else {
            return "pages-404_alt";
        }
    }

//    @PostMapping("/save")
//    public String save(Model model, @ModelAttribute(value = "modelForm") @Valid VendorDTO vendorDTO,
//                        BindingResult bindingResult,HttpSession session) {
//        if (bindingResult.hasErrors()) {
//            if (vendorDTO.getId() != null) {
//                model.addAttribute("tittlePage", "Edit Vendor");
//            } else {
//                model.addAttribute("tittlePage", "Add Vendor");
//            }
//            model.addAttribute("modelForm", vendorDTO);
//            model.addAttribute("viewOnly", false);
//            return "vendor-action";
//        }
//        var msg = "";
//        try {
//            vendorDTO.setActiveFlag(1);
//            customerService.saveVendor(vendorDTO);
//            if (vendorDTO.getId() != null) {
////            => method = update
//                msg = "Save Vendor Success !!!";
//            } else {
////                method = add
//                msg = "Add Vendor Success !!!";
//            }
//            session.setAttribute(Constant.MSG_SUCCESS, msg);
//        } catch (Exception e) {
//            session.setAttribute(Constant.MSG_ERROR, "Process Has ERROR !!!");
//            e.printStackTrace();
//        }
//        return "redirect:/admin/vendor/list";
//    }
}
