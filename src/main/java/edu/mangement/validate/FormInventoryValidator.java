package edu.mangement.validate;

import edu.mangement.model.FormInventory;
import edu.mangement.service.InventoryHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/27/2020
 * TIME : 11:47 PM
 */
public class FormInventoryValidator implements Validator {
    @Autowired
    private InventoryHistoryService inventoryHistoryService;
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == FormInventory.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        FormInventory formInventory = (FormInventory) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"inventoryCode","msg.inventory.inventoryCode.blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"productCode","msg.inventory.productCode.blank");
    }
}
