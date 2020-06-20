package edu.mangement.controller.activity;

import edu.mangement.constant.Constant;
import edu.mangement.model.BranchFeePerMonthDTO;
import edu.mangement.model.DateWorkDTO;
import edu.mangement.model.Paging;
import edu.mangement.service.BranchService;
import edu.mangement.service.DateWorkService;
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
@RequestMapping("/admin/date-work")
public class DateWorkController {
    @Autowired
    private DateWorkService dateWorkService;
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
        return "redirect:/admin/date-work/list/1";
    }

    @RequestMapping("/list/{page}")
    public String showRoleList(@ModelAttribute("searchForm") DateWorkDTO dateWorkDTO,
                               Model model, @PathVariable("page") int page, HttpSession session) {
        Paging paging = Paging.builder().recordPerPage(10).indexPage(page).build();
        var dateWorkDTOList = dateWorkService.search(dateWorkDTO,paging);
        if (paging.getIndexPage() < page && paging.getTotalPages() > 0) {
            return "redirect:/admin/date-work/list/1";
        }
        Constant.sessionProcessor(model, session);
        model.addAttribute("dateWorkDTOList", dateWorkDTOList);
        model.addAttribute("tittlePage", "Danh sách lương nhân viên");
        model.addAttribute("nameAddButton", "Nhập chi phí");
        model.addAttribute("paging", paging);
        initSelectBox(model);
        return "date-work";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("tittlePage", "Điểm danh");
        model.addAttribute("modelForm", new DateWorkDTO());
        model.addAttribute("viewOnly", false);
        initSelectBox(model);
        return "date-work-action";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") Long id) {
        var dateWorkDTO = dateWorkService.findDateWorkById(id);
        if (dateWorkDTO != null) {
            model.addAttribute("tittlePage", "Chi tiết điểm danh");
            model.addAttribute("modelForm", dateWorkDTO);
            model.addAttribute("viewOnly", true);
            initSelectBox(model);
            return "date-work-action";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        var dateWorkDTO = dateWorkService.findDateWorkById(id);
        if (dateWorkDTO != null) {
            model.addAttribute("tittlePage", "Sửa điểm danh");
            model.addAttribute("modelForm", dateWorkDTO);
            model.addAttribute("viewOnly", false);
            initSelectBox(model);
            return "date-work-action";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, HttpSession session) {
        var dateWorkDTO = dateWorkService.findDateWorkById(id);
        if (dateWorkDTO != null) {
            try {
                dateWorkService.deleteDateWork(dateWorkDTO);
                session.setAttribute(Constant.MSG_SUCCESS, "Delete Success !!!");
            } catch (Exception e) {
                session.setAttribute(Constant.MSG_ERROR, "Delete has Error !!!");
                e.printStackTrace();
            }
            return "redirect:/admin/date-work/list";
        } else {
            return "pages-404_alt";
        }
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute(value = "modelForm") @Valid DateWorkDTO dateWorkDTO,
                       BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            if (dateWorkDTO.getId() != null) {
                model.addAttribute("tittlePage", "Edit Date Work");
            } else {
                model.addAttribute("tittlePage", "Add Date Work");
            }
            model.addAttribute("modelForm", dateWorkDTO);
            model.addAttribute("viewOnly", false);
            initSelectBox(model);
            return "branch-fee-action";
        }
        var msg = "";
        try {
            dateWorkDTO.setActiveFlag(1);
            dateWorkService.saveDateWork(dateWorkDTO);
            if (dateWorkDTO.getId() != null) {
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
        return "redirect:/admin/date-work/list";
    }
    private void initSelectBox(Model model){
        var allBranch = branchService.findAllBranch(Pageable.unpaged());
        model.addAttribute("allBranch",allBranch);
    }
}
