package edu.mangement.controller.statis;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 6/2/2020
 * TIME : 10:04 AM

 */
@Controller
@RequestMapping("/admin/statistics")
public class StatisticsProductController {
    @GetMapping("/product")
    public String show(Model model){
        model.addAttribute("tittlePage","Số liệu về số sản phẩm bán được theo năm");
        model.addAttribute("tittleTable","Số liệu về sản phẩm");
        return "statistics-product";
    }

}
