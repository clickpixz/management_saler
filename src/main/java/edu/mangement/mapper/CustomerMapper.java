package edu.mangement.mapper;

import edu.mangement.entity.Customer;
import edu.mangement.model.CustomerDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:59 PM
 */
public class CustomerMapper {
    public static CustomerDTO toDTO(Customer customerEntity) {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(customerEntity.getId())
                .username(customerEntity.getUsername())
                .password(customerEntity.getPassword())
                .name(customerEntity.getName())
                .address(customerEntity.getAddress())
                .email(customerEntity.getEmail())
                .image(customerEntity.getImage())
                .phone(customerEntity.getPhone())
                .birthday(customerEntity.getBirthday())
                .createDate(customerEntity.getCreateDate())
                .updateDate(customerEntity.getUpdateDate())
                .activeFlag(customerEntity.getActiveFlag())
                .build();
//        if(customerEntity.getOrders()!=null){
//            customerDTO.setOrders(customerEntity.getOrders().stream().map(OrderMapper::toDTO).collect(Collectors.toList()));
//        }
        return customerDTO;
    }

    public static Customer toEntity(CustomerDTO customerDTO) {
        Customer customerEntity = Customer.builder()
                .id(customerDTO.getId())
                .username(customerDTO.getUsername())
                .password(customerDTO.getPassword())
                .name(customerDTO.getName())
                .address(customerDTO.getAddress())
                .email(customerDTO.getEmail())
                .image(customerDTO.getImage())
                .phone(customerDTO.getPhone())
                .birthday(customerDTO.getBirthday())
                .createDate(customerDTO.getCreateDate())
                .updateDate(customerDTO.getUpdateDate())
                .activeFlag(customerDTO.getActiveFlag())
                .build();
//        if(customerDTO.getOrders()!=null){
//            customerEntity.setOrders(customerDTO.getOrders().stream().map(OrderMapper::toEntity).collect(Collectors.toList()));
//        }
        return customerEntity;
    }
}
