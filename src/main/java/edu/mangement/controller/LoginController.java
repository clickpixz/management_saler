package edu.mangement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/17/2020
 * TIME : 7:47 PM
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(@RequestParam(required = false) String message,final Model model){
        if (message != null && !message.isEmpty()) {
            if (message.equals("logout")) {
                model.addAttribute("message", "Logout!");
            }
            if (message.equals("error")) {
                model.addAttribute("message", "Login Failed!");
            }
        }
        return "pages-login";
    }
    @RequestMapping("/403")
    public String accessDenied403() {
        return "pages-500";
    }
    @GetMapping("/logoutSuccessful")
    public String logoutSuccessful(){
        return "logout";
    }
}
