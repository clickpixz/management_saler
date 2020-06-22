package edu.mangement.controller.statis;

import edu.mangement.model.Paging;
import edu.mangement.service.InvoiceDetailService;
import edu.mangement.service.OrderDetailService;
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
public class StatisticsProductOnlineController {
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private InvoiceDetailService invoiceDetailService;
    @GetMapping("/order")
    public String show(Model model){
        Date date = DateFormatUtils3.getDateBasicType("2020-06-22 00:00:00");
        var topProductSellsOnline = orderDetailService.getTopProductSell(date, null);
        model.addAttribute("tittlePage","Số liệu về sản phẩm");
        model.addAttribute("tittleTable1","Sản phẩm bán online chạy nhất");
        model.addAttribute("topProductSellsOnline",topProductSellsOnline);
        return "statistics-product-online";
    }

}
