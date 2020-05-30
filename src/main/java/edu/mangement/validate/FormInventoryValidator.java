package edu.mangement.validate;

import edu.mangement.model.FormInventory;
import edu.mangement.repository.InventoryHistoryRepository;
import edu.mangement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/27/2020
 * TIME : 11:47 PM
 */
@Component
public class FormInventoryValidator implements Validator {
    @Autowired
    private InventoryHistoryRepository inventoryHistoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == FormInventory.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        FormInventory formInventory = (FormInventory) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inventoryCode", "msg.inventory.inventoryCode.blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productCode", "msg.inventory.productCode.blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "msg.inventory.quantity.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "msg.inventory.price.null");
        if (formInventory.getPrice().compareTo(BigDecimal.valueOf(0)) <= 0) {
            errors.rejectValue("price", "msg.inventory.price.value");
        }
        if (formInventory.getQuantity() <= 0) {
            errors.rejectValue("quantity", "msg.inventory.quantity.value");
        }
        if (inventoryHistoryRepository
                .findInventoryHistoryByCodeAndActiveFlag(formInventory.getInventoryCode(), 1) != null) {
            errors.rejectValue("inventoryCode", "msg.inventory.inventoryCode.exits");
        }
        if (productRepository.
                findProductByCodeAndActiveFlag(formInventory.getProductCode(), 1) == null) {
            errors.rejectValue("productCode", "msg.inventory.productCode.exits");
        }
    }
}
