package edu.mangement.controller.activity;

import edu.mangement.constant.Constant;
import edu.mangement.model.MemberDTO;
import edu.mangement.security.CustomUserDetail;
import edu.mangement.service.MemberService;
import edu.mangement.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 4:44 PM
 */
@Controller
public class HomeController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MenuService menuService;

    @RequestMapping("/admin/home")
    public String home(Model model, Principal principal, HttpSession session) {
        if (session.getAttribute(Constant.USER_INFO) == null) {
            CustomUserDetail customUserDetail = (CustomUserDetail) ((Authentication) principal).getPrincipal();
            MemberDTO member = memberService.findByUsername(customUserDetail.getUsername());
            if (session.getAttribute(Constant.MENU_SESSION) == null) {
                session.setAttribute(Constant.MENU_SESSION, menuService.generateMenu(member.getRole().getId()));
            }
            session.setAttribute(Constant.USER_INFO, member);
        }
        return "Main";
    }


}
