package edu.mangement.controller.activity;

import edu.mangement.constant.Constant;
import edu.mangement.model.VendorDTO;
import edu.mangement.service.VendorService;
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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/20/2020
 * TIME : 2:43 PM
 */
@Controller
@RequestMapping("/admin/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @InitBinder
    public void initBinder(WebDataBinder bind) {
        if (bind.getTarget() == null) {
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bind.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
    }

    @RequestMapping(value = {"/list", "/list/"})
    public String redirectShow() {
        return "redirect:/admin/vendor/list/1";
    }

    @GetMapping("/list/{page}")
    public String showCategoryList(Model model, @PathVariable("page") int page, HttpSession session) {
        var vendorDTOList = vendorService.findAllVendor(PageRequest.of(page - 1, 5));
        Constant.sessionProcessor(model, session);
        model.addAttribute("vendorDTOList", vendorDTOList);
        model.addAttribute("tittlePage","Danh sách các Vendor");
        model.addAttribute("nameAddButton","Thêm Vendor");
        return "vendor-list";
    }

    @GetMapping("/add")
    public String addBranch(Model model) {
        model.addAttribute("tittlePage", "Thêm Vendor");
        model.addAttribute("modelForm", new VendorDTO());
        model.addAttribute("viewOnly", false);
        return "vendor-action";
    }

    @GetMapping("/view/{id}")
    public String viewBranch(Model model, @PathVariable("id") Long id) {
        var vendorDTO = vendorService.findVendorById(id);
        if (vendorDTO != null) {
            model.addAttribute("tittlePage", "Chi tiết Vendor");
            model.addAttribute("modelForm", vendorDTO);
            model.addAttribute("viewOnly", true);
            return "vendor-action";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/edit/{id}")
    public String editBranch(Model model, @PathVariable("id") Long id) {
        var vendorDTO = vendorService.findVendorById(id);
        if (vendorDTO != null) {
            model.addAttribute("tittlePage", "Sửa Vendor");
            model.addAttribute("modelForm", vendorDTO);
            model.addAttribute("viewOnly", false);
            return "vendor-action";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBranch(Model model, @PathVariable("id") Long id, HttpSession session) {
        var vendorDTO = vendorService.findVendorById(id);
        if (vendorDTO != null) {
            try {
                vendorService.deleteVendor(vendorDTO);
                session.setAttribute(Constant.MSG_SUCCESS, "Delete Success !!!");
            } catch (Exception e) {
                session.setAttribute(Constant.MSG_ERROR, "Delete has Error !!!");
                e.printStackTrace();
            }
            return "redirect:/admin/vendor/list";
        } else {
            return "pages-404_alt";
        }
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute(value = "modelForm") @Valid VendorDTO vendorDTO,
                        BindingResult bindingResult,HttpSession session) {
        if (bindingResult.hasErrors()) {
            if (vendorDTO.getId() != null) {
                model.addAttribute("tittlePage", "Edit Vendor");
            } else {
                model.addAttribute("tittlePage", "Add Vendor");
            }
            model.addAttribute("modelForm", vendorDTO);
            model.addAttribute("viewOnly", false);
            return "vendor-action";
        }
        var msg = "";
        try {
            vendorDTO.setActiveFlag(1);
            vendorService.saveVendor(vendorDTO);
            if (vendorDTO.getId() != null) {
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
        return "redirect:/admin/vendor/list";
    }
}
