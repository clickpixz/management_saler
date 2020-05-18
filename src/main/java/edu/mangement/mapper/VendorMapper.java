package edu.mangement.mapper;

import edu.mangement.entity.Vendor;
import edu.mangement.model.dto.VendorDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 6:04 PM
 */
public class VendorMapper {
    public static VendorDTO toDTO(Vendor vendor) {
        return VendorDTO.builder()
                .id(vendor.getId())
                .name(vendor.getName())
                .phone(vendor.getPhone())
                .address(vendor.getAddress())
                .createDate(vendor.getCreateDate())
                .updateDate(vendor.getUpdateDate())
                .activeFlag(vendor.getActiveFlag())
                .products(vendor.getProducts().stream().map(ProductMapper::toDTO).collect(Collectors.toList()))
                .build();
    }
    public static Vendor toEntity(VendorDTO vendor) {
        return Vendor.builder()
                .id(vendor.getId())
                .name(vendor.getName())
                .phone(vendor.getPhone())
                .address(vendor.getAddress())
                .createDate(vendor.getCreateDate())
                .updateDate(vendor.getUpdateDate())
                .activeFlag(vendor.getActiveFlag())
                .products(vendor.getProducts().stream().map(ProductMapper::toEntity).collect(Collectors.toList()))
                .build();
    }
}
