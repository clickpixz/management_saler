package edu.mangement.mapper;

import edu.mangement.entity.Customer;
import edu.mangement.model.dto.CustomerDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:59 PM
 */
public class CustomerMapper {
    public static CustomerDTO toDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .username(customer.getUsername())
                .password(customer.getPassword())
                .name(customer.getName())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .image(customer.getImage())
                .phone(customer.getPhone())
                .birthday(customer.getBirthday())
                .createDate(customer.getCreateDate())
                .updateDate(customer.getUpdateDate())
                .activeFlag(customer.getActiveFlag())
                .orders(customer.getOrders().stream().map(OrderMapper::toDTO).collect(Collectors.toList()))
                .build();
    }

    public static Customer toEntity(CustomerDTO customer) {
        return Customer.builder()
                .id(customer.getId())
                .username(customer.getUsername())
                .password(customer.getPassword())
                .name(customer.getName())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .image(customer.getImage())
                .phone(customer.getPhone())
                .birthday(customer.getBirthday())
                .createDate(customer.getCreateDate())
                .updateDate(customer.getUpdateDate())
                .activeFlag(customer.getActiveFlag())
                .orders(customer.getOrders().stream().map(OrderMapper::toEntity).collect(Collectors.toList()))
                .build();
    }
}
