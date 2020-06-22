package edu.mangement.controller.activity;

import edu.mangement.model.OrderDetailDTO;
import edu.mangement.model.OrderFilterForm;
import edu.mangement.model.Paging;
import edu.mangement.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/20/2020
 * TIME : 2:43 PM
 */
@Controller
@RequestMapping("/admin/order-detail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

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
        return "redirect:/admin/order-detail/list/1";
    }

    @RequestMapping("/list/{page}")
    public String show(Model model, @RequestParam(name = "orderId", defaultValue = "0") Long orderId,
                       @PathVariable("page") int page) {
        Paging paging = Paging.builder().recordPerPage(10).indexPage(page).build();
        var orderDetailDTOList = orderDetailService
                .findOrderDetailByOrderId(orderId, paging, PageRequest.of(page - 1, 10));
        if (paging.getIndexPage() < page && paging.getTotalPages() > 0) {
            return "redirect:/admin/order-detail/list/1/";
        }
        model.addAttribute("orderDetailDTOList", orderDetailDTOList);
        model.addAttribute("tittlePage", "Danh sách chi tiết đơn hàng online");
        model.addAttribute("paging", paging);
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderDetailDTO orderDetailDTO : orderDetailDTOList) {
            sum = sum.add(orderDetailDTO.getUnitPrice().multiply(new BigDecimal(orderDetailDTO.getQuantity())));
        }
        model.addAttribute("sum", sum);
        return "order-detail-list";
    }
}
