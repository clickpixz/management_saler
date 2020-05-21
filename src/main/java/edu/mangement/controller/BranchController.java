package edu.mangement.controller;

import edu.mangement.constant.Constant;
import edu.mangement.model.BranchDTO;
import edu.mangement.service.BranchService;
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
@RequestMapping("/admin/branch")
public class BranchController {
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
        return "redirect:/admin/branch/list/1";
    }

    @GetMapping("/list/{page}")
    public String showCategoryList(Model model, @PathVariable("page") int page, HttpSession session) {
        List<BranchDTO> branchDTOList = branchService.findAllBranch(PageRequest.of(page - 1, 5));
        Constant.sessionProcessor(model, session);
        model.addAttribute("branchDTOList", branchDTOList);
        return "branch-list";
    }

    @GetMapping("/add")
    public String addBranch(Model model) {
        model.addAttribute("tittlePage", "Thêm chi nhánh");
        model.addAttribute("modelForm", new BranchDTO());
        model.addAttribute("viewOnly", false);
        return "branch-action";
    }

    @GetMapping("/view/{id}")
    public String viewBranch(Model model, @PathVariable("id") Long id) {
        BranchDTO branchDTO = branchService.findBranchById(id);
        if (branchDTO != null) {
            model.addAttribute("tittlePage", "Chi tiết chi nhánh");
            model.addAttribute("modelForm", branchDTO);
            model.addAttribute("viewOnly", true);
            return "branch-action";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/edit/{id}")
    public String editBranch(Model model, @PathVariable("id") Long id) {
        BranchDTO branchDTO = branchService.findBranchById(id);
        if (branchDTO != null) {
            model.addAttribute("tittlePage", "Sửa chi nhánh");
            model.addAttribute("modelForm", branchDTO);
            model.addAttribute("viewOnly", false);
            return "branch-action";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBranch(Model model, @PathVariable("id") Long id, HttpSession session) {
        BranchDTO branchDTO = branchService.findBranchById(id);
        if (branchDTO != null) {
            model.addAttribute("viewOnly", false);
            try {
                branchService.deleteBranch(branchDTO);
                session.setAttribute(Constant.MSG_SUCCESS, "Delete Success !!!");
            } catch (Exception e) {
                session.setAttribute(Constant.MSG_ERROR, "Delete has Error !!!");
                e.printStackTrace();
            }
            return "redirect:/admin/branch/list";
        } else {
            return "pages-404_alt";
        }
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute(value = "modelForm") @Valid BranchDTO branchDTO,
                        BindingResult bindingResult,HttpSession session) {
        if (bindingResult.hasErrors()) {
            if (branchDTO.getId() != null) {
                model.addAttribute("tittlePage", "Edit Branch");
            } else {
                model.addAttribute("tittlePage", "Add Branch");
            }
            System.out.println(branchDTO);
            model.addAttribute("modelForm", branchDTO);
            model.addAttribute("viewOnly", false);
            return "branch-action";
        }
        var msg = "";
        try {
            branchDTO.setActiveFlag(1);
            branchService.saveBranch(branchDTO);
            if (branchDTO.getId() != null) {
//            => method = update
                msg = "Save Branch Success !!!";
            } else {
//                method = add
                msg = "Add Branch Success !!!";
            }
            session.setAttribute(Constant.MSG_SUCCESS, msg);
        } catch (Exception e) {
            session.setAttribute(Constant.MSG_ERROR, "Process Has ERROR !!!");
            e.printStackTrace();
        }
        return "redirect:/admin/branch/list";
    }
}
