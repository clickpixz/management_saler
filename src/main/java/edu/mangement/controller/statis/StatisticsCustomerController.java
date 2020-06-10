package edu.mangement.controller.statis;

import edu.mangement.service.InvoiceDetailService;
import edu.mangement.utils.DateFormatUtils3;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StatisticsCustomerController {
    @GetMapping("/customer")
    public String show(Model model){
        Date date = new Date();
        model.addAttribute("tittlePage","Số liệu về số lượng khách hàng online mới trong 30 ngày qua");
        model.addAttribute("tittleTable","Sản phẩm bán offline chạy nhất");
        return "statistics-customer";
    }

}
