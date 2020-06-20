package edu.mangement.controller.activity;

import edu.mangement.constant.Constant;
import edu.mangement.model.OrderFilterForm;
import edu.mangement.model.Paging;
import edu.mangement.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/20/2020
 * TIME : 2:43 PM
 */
@Controller
@RequestMapping("/admin/invoice")
public class InvoiceController {
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

    @RequestMapping(value = {"/list", "/list/"})
    public String redirectShow() {
        return "redirect:/admin/invoice/list/1";
    }

    @RequestMapping("/list/{page}")
    public String show(@ModelAttribute("searchForm") OrderFilterForm orderFilterForm, Model model, @PathVariable("page") int page, HttpSession session) {
        Paging paging = Paging.builder().recordPerPage(10).indexPage(page).build();
        var invoiceDTOList = invoiceService.search(orderFilterForm,paging);
        if (paging.getIndexPage() < page && paging.getTotalPages() > 0) {
            return "redirect:/admin/order/list/1";
        }
        Constant.sessionProcessor(model, session);
        model.addAttribute("invoiceDTOList", invoiceDTOList);
        model.addAttribute("tittlePage", "Danh sách các đơn hàng tại các chi nhánh");
        model.addAttribute("paging", paging);
        return "invoice-list";
    }
}
