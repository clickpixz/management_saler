package edu.mangement.controller.api;

import edu.mangement.model.form.api.Wraps;
import edu.mangement.service.InvoiceDetailService;
import edu.mangement.service.OrderDetailService;
import edu.mangement.utils.DateFormatUtils3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 6/7/2020
 * TIME : 1:03 AM
 */
@RestController
@RequestMapping("/admin/api/v1")
public class StatisticsProductRestController {
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private InvoiceDetailService invoiceDetailService;
    @GetMapping("/top-order-online")
    public ResponseEntity<?> getTopOrderOnline(){
        Date date = DateFormatUtils3.getDateBasicType("2020-05-25 00:00:00");
        var topProductSell = orderDetailService.getTopProductSell(date, null);
        return ResponseEntity.ok(new Wraps(topProductSell));
    }

    @GetMapping("/top-invoice-offline")
    public ResponseEntity<?> getTopInvoiceOffline() {
        Date date = DateFormatUtils3.getDateBasicType("2020-05-25 00:00:00");
        var topProductSell = invoiceDetailService.getTopProductSell(date, null);
        return ResponseEntity.ok(new Wraps(topProductSell));
    }

}
