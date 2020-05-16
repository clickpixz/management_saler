package edu.mangement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/16/2020
 * TIME : 4:44 PM
 */
@Controller("/branch")
public class HelloController {
    @GetMapping("/demo")
    public String hello(){
        return "index";
    }
}
