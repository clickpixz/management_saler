package edu.mangement.validate;

import edu.mangement.model.MemberDTO;
import edu.mangement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/26/2020
 * TIME : 5:51 PM
 */
@Component
public class MemberValidator implements Validator {
    @Autowired
    private MemberService memberService;
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == MemberDTO.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        MemberDTO memberDTO = (MemberDTO) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","msg.member.username.blank");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","msg.member.password.blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","msg.member.name.blank");
        //check username exits
        if(memberDTO.getUsername()!=null){
            var result = memberService.findByUsername(memberDTO.getUsername());
            if(result!=null){
            //check method save or update
                if (memberDTO.getId()!=null&&memberDTO.getId()>0) {
                    //co id la update
                    if(result.getId()!=memberDTO.getId()){
                        //neu id khac voi id tim dc tuc la bi trung username
                        errors.rejectValue("username","msg.member.username.exits");
                    }
                }else {
                    //la save nhung lai tim duoc user name tuong ung => trung username
                        errors.rejectValue("username","msg.member.username.exits");
                }
            }
        }
        //check lenght username
        if (memberDTO.getUsername()!=null&&memberDTO.getUsername().length()<=6&&memberDTO.getUsername().length()>=50){
            errors.rejectValue("username","msg.member.username.length");
        }
        //check password lenght
//        if(memberDTO.getPassword()!=null&&memberDTO.getPassword().length()<=6&&memberDTO.getPassword().length()>=30){
//            errors.rejectValue("password","msg.member.password.length");
//        }
        //checl salary value
        if(memberDTO.getSalary()!=null&&memberDTO.getSalary().compareTo(BigDecimal.valueOf(0))<0){
            errors.rejectValue("salary","msg.member.salary.value");
        }
    }
}
