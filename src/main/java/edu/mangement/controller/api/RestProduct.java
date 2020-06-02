package edu.mangement.controller.api;

import edu.mangement.model.FormPushProduct;
import edu.mangement.model.form.api.AjaxResponse;
import edu.mangement.model.form.api.FormRevokeItems;
import edu.mangement.service.ItemsService;
import edu.mangement.service.ProductInStockService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RestProduct {
    @Autowired
    private ProductInStockService productInStockService;
    @Autowired
    private ItemsService itemsService;

    @PostMapping("/push")
    public ResponseEntity<?> pushUp(@RequestBody FormPushProduct formPushProduct) {
        try {
            productInStockService.pushProduct(formPushProduct);
            return ResponseEntity.ok(new AjaxResponse("Insert success !!!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/revoke")
    public ResponseEntity<?> revokeItems(@RequestBody FormRevokeItems formRevokeItems){
        try {
            itemsService.revokeItems(formRevokeItems);
            return ResponseEntity.ok(new AjaxResponse("Revoke Success !!!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
