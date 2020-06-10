package edu.mangement.controller.api;

import edu.mangement.model.form.api.Wraps;
import edu.mangement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/v1")
public class StatisticsCustomerRestController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/customer")
    public ResponseEntity<?> countCustomer30DayLeft(){
        Map<String, Long> map = customerService.countCustomerRegisters30DaysLeft(new Date());
        return ResponseEntity.ok(new Wraps(map));
    }
}
