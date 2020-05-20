package edu.mangement.mapper;

import edu.mangement.entity.Vendor;
import edu.mangement.model.VendorDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 6:04 PM
 */
public class VendorMapper {
    public static VendorDTO toDTO(Vendor vendorEntity) {
        VendorDTO vendorDTO = VendorDTO.builder()
                .id(vendorEntity.getId())
                .name(vendorEntity.getName())
                .phone(vendorEntity.getPhone())
                .address(vendorEntity.getAddress())
                .createDate(vendorEntity.getCreateDate())
                .updateDate(vendorEntity.getUpdateDate())
                .activeFlag(vendorEntity.getActiveFlag())
                .build();
//        if(vendorEntity.getProducts()!=null){
//            vendorDTO.setProducts(vendorEntity.getProducts().stream().map(ProductMapper::toDTO).collect(Collectors.toList()));
//        }
        return vendorDTO;
    }
    public static Vendor toEntity(VendorDTO vendorDTO) {
        Vendor vendorEntity = Vendor.builder()
                .id(vendorDTO.getId())
                .name(vendorDTO.getName())
                .phone(vendorDTO.getPhone())
                .address(vendorDTO.getAddress())
                .createDate(vendorDTO.getCreateDate())
                .updateDate(vendorDTO.getUpdateDate())
                .activeFlag(vendorDTO.getActiveFlag())
                .build();
//        if(vendorDTO.getProducts()!=null){
//            vendorEntity.setProducts(vendorDTO.getProducts().stream().map(ProductMapper::toEntity).collect(Collectors.toList()));
//        }
        return vendorEntity;
    }
}
