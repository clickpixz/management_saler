package edu.mangement.controller.api;

import edu.mangement.entity.sp.DayQuantityMapper;
import edu.mangement.model.form.api.AjaxResponse;
import edu.mangement.model.form.api.SearchCriteria;
import edu.mangement.model.form.api.Wraps;
import edu.mangement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

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
    @Autowired
    private OrderService orderService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ProductService productService;
    @GetMapping("/top-order-online")
    public ResponseEntity<?> getTopOrderOnline() {
        LocalDate localDate = LocalDate.of(2020,6,22);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        var topProductSell = orderDetailService.getTopProductSell(date, null);
        return ResponseEntity.ok(new Wraps(topProductSell));
    }

    @GetMapping("/total-order-by-day")
    public ResponseEntity<?> getTotalOrderByDay() {
        LocalDate localDate = LocalDate.of(2020,6,22);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        var map = orderService.countOrderByDay(date);
        return ResponseEntity.ok(new Wraps(map));
    }

    @GetMapping("/top-invoice-offline")
    public ResponseEntity<?> getTopInvoiceOffline() {
        LocalDate localDate = LocalDate.of(2020,6,22);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        var topProductSell = invoiceDetailService.getTopProductSell(date, null);
        return ResponseEntity.ok(new Wraps(topProductSell));
    }

    @GetMapping("/total-invoice-by-day")
    public ResponseEntity<?> getTotalInvoiceByDay() {
        LocalDate localDate = LocalDate.of(2020,6,22);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        var map = invoiceService.countInvoiceByDay(date);
        return ResponseEntity.ok(new Wraps(map));
    }
    @PostMapping("/count-product-by-year")
    public ResponseEntity<?> countProductSoldInYear(@Valid @RequestBody SearchCriteria searchCriteria, Errors errors){
        AjaxResponse result = new AjaxResponse();
        if(errors.hasErrors()){
            result.setMessage(errors.getAllErrors()
                    .stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }
        var product = productService.findProductByCode(searchCriteria.getName());
        if(product==null){
            result.setMessage("Product Not Found !!!");
            return ResponseEntity.badRequest().body(result);
        }
        LocalDate localDate = LocalDate.of(2020,6,22);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        var list = productService.contProductSold(date, product.getId());
        result.setMessage(product.getName());
        result.setList(list);
        var average = list.stream().mapToLong(DayQuantityMapper::getQuantity).average().getAsDouble();
        result.setAverage((long) average);
        return ResponseEntity.ok(result);
    }

}
