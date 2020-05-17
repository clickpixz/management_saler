package edu.mangement.controller;

import edu.mangement.entity.Function;
import edu.mangement.entity.Member;
import edu.mangement.security.CustomUserDetail;
import edu.mangement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

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

    @RequestMapping("/admin/home")
    public String home(Model model, Principal principal, HttpSession session) {
        CustomUserDetail customUserDetail = (CustomUserDetail) ((Authentication) principal).getPrincipal();
        Member member = memberService.findByUsername(customUserDetail.getUsername());
        generateMenuBar(session, member);
        return "Main";
    }

    private void generateMenuBar(HttpSession session, Member member) {
        var role = member.getRole();
        //danh sach menu cha
        var parentMenuList = new ArrayList<Function>();
        //danh sach menu con
        var childMenuList = new ArrayList<Function>();
        role.getAuths().forEach(auth -> {
            var menu = auth.getFunction();
            //lay tat ca cac menu con va menu cha duoc phep su dung
            if (menu.getParentId() == 1 && menu.getOrderIndex() != -1&& menu.getActiveFlag()==1
                &&auth.getPermission()==1 &&auth.getActiveFlag()==1){
            }

        });
        session.setAttribute("name", member.getName());
    }
}
