package edu.mangement.mapper;

import edu.mangement.entity.Invoice;
import edu.mangement.model.InvoiceDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:52 PM
 */
public class InvoiceMapper {
    public static InvoiceDTO toDTO(Invoice invoiceEntity) {
        InvoiceDTO invoiceDTO = InvoiceDTO.builder()
                .id(invoiceEntity.getId())
                .nameCustomer(invoiceEntity.getNameCustomer())
                .phone(invoiceEntity.getPhone())
                .totalOrder(invoiceEntity.getTotalOrder())
                .createDate(invoiceEntity.getCreateDate())
                .updateDate(invoiceEntity.getUpdateDate())
                .activeFlag(invoiceEntity.getActiveFlag())
                .build();
        if(invoiceEntity.getMember()!=null){
            invoiceDTO.setMember(MemberMapper.toDTO(invoiceEntity.getMember()));
        }
        if(invoiceEntity.getInvoiceDetails()!=null){
            invoiceDTO.setInvoiceDetails(invoiceEntity.getInvoiceDetails().stream().map(InvoiceDetailMapper::toDTO).collect(Collectors.toList()));
        }
        return invoiceDTO;
    }

    public static Invoice toEntity(InvoiceDTO invoiceDTO) {
        Invoice invoiceEntity = Invoice.builder()
                .id(invoiceDTO.getId())
                .nameCustomer(invoiceDTO.getNameCustomer())
                .phone(invoiceDTO.getPhone())
                .totalOrder(invoiceDTO.getTotalOrder())
                .createDate(invoiceDTO.getCreateDate())
                .updateDate(invoiceDTO.getUpdateDate())
                .activeFlag(invoiceDTO.getActiveFlag())
                .build();
        if(invoiceDTO.getMember()!=null){
            invoiceEntity.setMember(MemberMapper.toEntity(invoiceDTO.getMember()));
        }
        if(invoiceDTO.getInvoiceDetails()!=null){
            invoiceEntity.setInvoiceDetails(invoiceDTO.getInvoiceDetails().stream().map(InvoiceDetailMapper::toEntity).collect(Collectors.toList()));
        }
        return invoiceEntity;
    }
}
