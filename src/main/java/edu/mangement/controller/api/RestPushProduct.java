package edu.mangement.controller.api;

import edu.mangement.constant.Constant;
import edu.mangement.model.FormPushProduct;
import edu.mangement.service.ProductInStockService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/31/2020
 * TIME : 3:28 PM
 */
@RestController
@RequestMapping("/admin/api/v1")
public class RestPushProduct {
    @Autowired
    private ProductInStockService productInStockService;

    @PostMapping("/push")
    public ResponseEntity<?> pushUp(@RequestBody FormPushProduct formPushProduct, HttpSession session) {
        try {
            productInStockService.pushProduct(formPushProduct);
            return ResponseEntity.ok(new AjaxResponse("Insert success !!!"));
        } catch (Exception e) {
            System.out.println("aaaa");
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class AjaxResponse{
        private String message;
    }
}
