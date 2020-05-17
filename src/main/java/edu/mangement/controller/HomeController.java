package edu.mangement.controller;

import edu.mangement.security.CustomUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 4:44 PM
 */
@Controller
public class HomeController {
    @RequestMapping("/admin/home")
    public String home(Model model, Principal principal){
        CustomUserDetail customUserDetail = (CustomUserDetail) ((Authentication) principal).getPrincipal();
        model.addAttribute("username",customUserDetail.getUsername());
        return "Main";
    }
}
