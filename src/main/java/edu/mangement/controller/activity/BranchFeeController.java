package edu.mangement.controller.activity;

import edu.mangement.constant.Constant;
import edu.mangement.model.BranchFeePerMonthDTO;
import edu.mangement.model.Paging;
import edu.mangement.service.BranchFeeService;
import edu.mangement.service.BranchService;
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
@RequestMapping("/admin/branch-fee-per-month")
public class BranchFeeController {
    @Autowired
    private BranchFeeService branchFeeService;
    @Autowired
    private BranchService branchService;
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
        return "redirect:/admin/branch-fee-per-month/list/1";
    }

    @RequestMapping("/list/{page}")
    public String showRoleList(@ModelAttribute("searchForm") BranchFeePerMonthDTO branchFeePerMonthDTO,
                               Model model, @PathVariable("page") int page, HttpSession session) {
        Paging paging = Paging.builder().recordPerPage(10).indexPage(page).build();
        var branchFeePerMonthDTOList = branchFeeService.search(branchFeePerMonthDTO,paging);
        if (paging.getIndexPage() < page && paging.getTotalPages() > 0) {
            return "redirect:/admin/branch-fee-per-month/list/1";
        }
        Constant.sessionProcessor(model, session);
        model.addAttribute("branchFeePerMonthDTOList", branchFeePerMonthDTOList);
        model.addAttribute("tittlePage", "Danh sách chi phí hàng tháng");
        model.addAttribute("nameAddButton", "Nhập chi phí");
        model.addAttribute("paging", paging);
        initSelectBox(model);
        return "branch-fee-per-month-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("tittlePage", "Thêm Chi phí");
        model.addAttribute("modelForm", new BranchFeePerMonthDTO());
        model.addAttribute("viewOnly", false);
        initSelectBox(model);
        return "branch-fee-action";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") Long id) {
        var branchFeePerMonthDTO = branchFeeService.findBranchFeeById(id);
        if (branchFeePerMonthDTO != null) {
            model.addAttribute("tittlePage", "Chi tiết Chi phí");
            model.addAttribute("modelForm", branchFeePerMonthDTO);
            model.addAttribute("viewOnly", true);
            initSelectBox(model);
            return "branch-fee-action";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        var branchFeePerMonthDTO = branchFeeService.findBranchFeeById(id);
        if (branchFeePerMonthDTO != null) {
            model.addAttribute("tittlePage", "Sửa Chi phí");
            model.addAttribute("modelForm", branchFeePerMonthDTO);
            model.addAttribute("viewOnly", false);
            initSelectBox(model);
            return "branch-fee-action";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id, HttpSession session) {
        var branchFeePerMonthDTO = branchFeeService.findBranchFeeById(id);
        if (branchFeePerMonthDTO != null) {
            try {
                branchFeeService.deleteBranchFee(branchFeePerMonthDTO);
                session.setAttribute(Constant.MSG_SUCCESS, "Delete Success !!!");
            } catch (Exception e) {
                session.setAttribute(Constant.MSG_ERROR, "Delete has Error !!!");
                e.printStackTrace();
            }
            return "redirect:/admin/branch-fee-per-month/list";
        } else {
            return "pages-404_alt";
        }
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute(value = "modelForm") @Valid BranchFeePerMonthDTO branchFeePerMonthDTO,
                       BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            if (branchFeePerMonthDTO.getId() != null) {
                model.addAttribute("tittlePage", "Edit Branch Fee");
            } else {
                model.addAttribute("tittlePage", "Add Branch Fee");
            }
            model.addAttribute("modelForm", branchFeePerMonthDTO);
            model.addAttribute("viewOnly", false);
            initSelectBox(model);
            return "branch-fee-action";
        }
        var msg = "";
        try {
            branchFeePerMonthDTO.setActiveFlag(1);
            branchFeeService.saveBranchFee(branchFeePerMonthDTO);
            if (branchFeePerMonthDTO.getId() != null) {
//            => method = update
                msg = "Save Fee Success !!!";
            } else {
//                method = add
                msg = "Add Fee Success !!!";
            }
            session.setAttribute(Constant.MSG_SUCCESS, msg);
        } catch (Exception e) {
            session.setAttribute(Constant.MSG_ERROR, "Process Has ERROR !!!");
            e.printStackTrace();
        }
        return "redirect:/admin/branch-fee-per-month/list";
    }
    private void initSelectBox(Model model){
        var allBranch = branchService.findAllBranch(Pageable.unpaged());
        model.addAttribute("allBranch",allBranch);
    }
}
