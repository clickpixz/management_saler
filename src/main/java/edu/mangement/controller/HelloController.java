package edu.mangement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 4:44 PM
 */
@Controller
public class HelloController {
    @GetMapping("/dashboard-2")
    public String hello(){
        return "Main";
    }
}
