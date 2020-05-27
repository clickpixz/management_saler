package edu.mangement.controller;

import edu.mangement.constant.Constant;
import edu.mangement.model.*;
import edu.mangement.service.BranchService;
import edu.mangement.service.MemberService;
import edu.mangement.service.RoleService;
import edu.mangement.validate.MemberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("/admin/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private BranchService branchService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MemberValidator memberValidator;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private Environment environment;
    @InitBinder
    public void initBinder(WebDataBinder bind) {
        if (bind.getTarget() == null) {
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bind.registerCustomEditor(Data.class, new CustomDateEditor(simpleDateFormat, false));
        if (bind.getTarget().getClass() == MemberDTO.class) {
            bind.setValidator(memberValidator);
        }
    }

    @RequestMapping(value = {"/list", "/list/"})
    public String redirectShow() {
        return "redirect:/admin/member/list/1";
    }

    @RequestMapping("/list/{page}")
    public String show(@ModelAttribute("searchForm") SearchForm searchForm, Model model, @PathVariable("page") int page, HttpSession session) {
        Paging paging = Paging.builder().recordPerPage(2).indexPage(page).build();
        List<MemberDTO> memberDTOList;
        if (searchForm != null && searchForm.getField() != null && !searchForm.getField().isBlank()) {
            memberDTOList = memberService.search(searchForm, paging);
        } else {
            memberDTOList = memberService.findAll(PageRequest.of(page - 1, 2),paging);
            if (paging.getIndexPage() < page&&paging.getTotalPages() > 0) {
                return "redirect:/admin/member/list/1";
            }
        }
        Constant.sessionProcessor(model, session);
        model.addAttribute("memberDTOList", memberDTOList);
        model.addAttribute("tittlePage", "Danh sách các Nhân Viên");
        model.addAttribute("nameAddButton", "Thêm Nhân Viên");
        model.addAttribute("paging",paging);
        return "member-list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        initSelectBox(model);
        model.addAttribute("tittlePage", "Thêm Nhân Viên");
        model.addAttribute("modelForm", new MemberDTO());
        model.addAttribute("viewOnly", false);
        return "member-action";
    }

    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable("id") Long id) {
        var memberDTO = memberService.findById(id);
        if (memberDTO != null) {
            memberDTO.setBranchId(memberDTO.getBranch().getId());
            memberDTO.setRoleId(memberDTO.getRole().getId());
            model.addAttribute("tittlePage", "Chi tiết Nhân Viên");
            model.addAttribute("modelForm", memberDTO);
            model.addAttribute("viewOnly", true);
            return "member-action";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        var memberDTO = memberService.findById(id);
        if (memberDTO != null) {
            memberDTO.setBranchId(memberDTO.getBranch().getId());
            memberDTO.setRoleId(memberDTO.getRole().getId());
            model.addAttribute("tittlePage", "Sửa Thông tin Nhân Viên");
            model.addAttribute("modelForm", memberDTO);
            model.addAttribute("viewOnly", false);
            initSelectBox(model);
            return "member-action";
        } else {
            return "pages-404_alt";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, HttpSession session) {
        var memberDTO = memberService.findById(id);
        if (memberDTO != null) {
            try {
                memberService.deleteMember(memberDTO);
                session.setAttribute(Constant.MSG_SUCCESS, "Delete Success !!!");
            } catch (Exception e) {
                session.setAttribute(Constant.MSG_ERROR, "Delete has Error !!!");
                e.printStackTrace();
            }
            return "redirect:/admin/member/list";
        } else {
            return "pages-404_alt";
        }
    }

    @PostMapping("/save")
    public String save(Model model, @ModelAttribute(value = "modelForm") @Valid MemberDTO memberDTO,
                       BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            System.out.println(memberDTO);
            if (memberDTO.getId() != null) {
                model.addAttribute("tittlePage", "Edit Member");
            } else {
                model.addAttribute("tittlePage", "Add Member");
            }
            initSelectBox(model);
            model.addAttribute("modelForm", memberDTO);
            model.addAttribute("viewOnly", false);
            return "member-action";
        }
        var msg = "";
        try {
            var branchDTO = BranchDTO.builder().id(memberDTO.getBranchId()).build();
            var roleDTO = RoleDTO.builder().id(memberDTO.getRoleId()).build();
            memberDTO.setBranch(branchDTO);
            memberDTO.setRole(roleDTO);
            memberDTO.setActiveFlag(1);
            if (memberDTO.getId() != null) {
//            => method = update
                msg = "Save Vendor Success !!!";
            } else {
//                method = add
                msg = "Add Vendor Success !!!";
                memberDTO.setPassword(passwordEncoder.encode(environment.getProperty("member.password.default")));
            }
            memberService.saveMember(memberDTO);
            session.setAttribute(Constant.MSG_SUCCESS, msg);
        } catch (Exception e) {
            session.setAttribute(Constant.MSG_ERROR, "Process Has ERROR !!!");
            e.printStackTrace();
        }
        return "redirect:/admin/member/list";
    }

    private void initSelectBox(Model model) {
        Map<Long, String> allRole = roleService.findAllRole(Pageable.unpaged())
                .stream().collect(Collectors.toMap(RoleDTO::getId, RoleDTO::getName, (o, n) -> n, HashMap::new));
        Map<Long, String> allBranch = branchService.findAllBranch(Pageable.unpaged())
                .stream().collect(Collectors.toMap(BranchDTO::getId, BranchDTO::getName, (o, n) -> n, HashMap::new));
        model.addAttribute("mapRole", allRole);
        model.addAttribute("mapBranch", allBranch);
    }
}
