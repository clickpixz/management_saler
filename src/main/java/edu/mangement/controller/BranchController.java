package edu.mangement.controller;

import edu.mangement.constant.Constant;
import edu.mangement.model.BranchDTO;
import edu.mangement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
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
    @RequestMapping(value = {"/list","/list/"})
    public String redirectShow(){
        return "redirect:/admin/branch/list/1";
    }
    @GetMapping("/list/{page}")
    public String showCategoryList(Model model, @PathVariable("page") int page,HttpSession session){
        List<BranchDTO> branchDTOList = branchService.findAllBranch(PageRequest.of(page-1, 5));
        Constant.sessionProcessor(model,session);
        model.addAttribute("branchDTOList",branchDTOList);
        return "branch-list";
    }
    @GetMapping("/add")
    public String addBranch(Model model){
        model.addAttribute("tittlePage","Thêm chi nhánh");
        model.addAttribute("modelForm",new BranchDTO());
        model.addAttribute("viewOnly",false);
        return "branch-action";
    }
    @GetMapping("/view/{id}")
    public String viewBranch(Model model,@PathVariable("id") Long id){
        BranchDTO branchDTO = branchService.findBranchById(id);
        if(branchDTO!=null){
            model.addAttribute("tittlePage","Chi tiết chi nhánh");
            model.addAttribute("modelForm",branchDTO);
            model.addAttribute("viewOnly",true);
            return "branch-action";
        }else {
            return "pages-404_alt";
        }
    }
    @GetMapping("/edit/{id}")
    public String editBranch(Model model,@PathVariable("id") Long id){
        BranchDTO branchDTO = branchService.findBranchById(id);
        if(branchDTO!=null){
            model.addAttribute("tittlePage","Sửa chi nhánh");
            model.addAttribute("modelForm",branchDTO);
            model.addAttribute("viewOnly",false);
            return "branch-action";
        }else {
            return "pages-404_alt";
        }
    }
    @GetMapping("/delete/{id}")
    public String deleteBranch(Model model, @PathVariable("id") Long id, HttpSession session){
        BranchDTO branchDTO = branchService.findBranchById(id);
        if(branchDTO!=null){
            model.addAttribute("viewOnly",false);
            try {
                branchService.deleteBranch(branchDTO);
                session.setAttribute(Constant.MSG_SUCCESS,"Delete Success !!!");
            } catch (Exception e) {
                session.setAttribute(Constant.MSG_ERROR,"Delete has Error !!!");
                e.printStackTrace();
            }
            return "redirect:/admin/branch/list";
        }else {
            return "pages-404_alt";
        }
    }
}
