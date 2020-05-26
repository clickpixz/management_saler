package edu.mangement.validate;

import edu.mangement.model.ProductDTO;
import edu.mangement.service.ProductService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/25/2020
 * TIME : 5:29 PM
 */
@Component
public class ProductValidator implements Validator {
    @Autowired
    private ProductService productService;

    @Override
    public boolean supports(Class<?> zClass) {
        return zClass == ProductDTO.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProductDTO productDTO = (ProductDTO) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","msg.product.name.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"code","msg.product.code.null");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"material","msg.product.material.null");
        if (productDTO.getCode() != null) {
            ProductDTO result = productService.findProductByCode(productDTO.getCode());
            if (result != null) {
//                kiem tra xem la save hay update
//                neu co id thi la update
                if (productDTO.getId() != null && productDTO.getId() != 0) {
//                kiem tra id voi id cua result neu ko trung thi la trung code con trung thi thoa man
                    if(result.getId()!=productDTO.getId()){
                        errors.rejectValue("code","msg.product.code.exits");
                    }
                }else {
//                    => ko co id => tao moi product => result != null => trung code
                    errors.rejectValue("code","msg.product.code.exits");
                }
            }
        }
        if (!productDTO.getMultipartFile().getOriginalFilename().isBlank()) {
            String extension = FilenameUtils.getExtension(productDTO.getMultipartFile().getOriginalFilename());
            if (!extension.equals("jpg")&&!extension.equals("png")) {
                errors.rejectValue("multipartFile","msg.file.extension.error");
            }
        }
    }
}
