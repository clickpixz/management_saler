package edu.mangement.controller;

import edu.mangement.model.BranchDTO;
import edu.mangement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String showCategoryList(Model model, @PathVariable("page") int page){
        List<BranchDTO> branchDTOList = branchService.findAllBranch(PageRequest.of(page-1, 5));
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
        return "branch-action";
    }
}
