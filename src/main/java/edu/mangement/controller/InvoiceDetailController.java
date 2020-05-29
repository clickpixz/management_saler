package edu.mangement.controller;

import edu.mangement.model.Paging;
import edu.mangement.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/20/2020
 * TIME : 2:43 PM
 */
@Controller
@RequestMapping("/admin/invoice-detail")
public class InvoiceDetailController {
    @Autowired
    private InvoiceService invoiceService;

    @InitBinder
    public void initBinder(WebDataBinder bind) {
        if (bind.getTarget() == null) {
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bind.registerCustomEditor(Data.class, new CustomDateEditor(simpleDateFormat, false));
    }

    @RequestMapping("/list")
    public String show(Model model, @RequestParam(name = "invoiceId", defaultValue = "0") Long invoiceId) {
        var invoiceDTO = invoiceService.getById(invoiceId);
        model.addAttribute("invoiceDTO", invoiceDTO);
        model.addAttribute("tittlePage", "Danh sách chi tiết đơn hàng tại các chi nhánh");
        return "invoice-detail-list";
    }
}
