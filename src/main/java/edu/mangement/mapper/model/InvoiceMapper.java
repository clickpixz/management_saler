package edu.mangement.mapper.model;

import edu.mangement.entity.Invoice;
import edu.mangement.model.dto.InvoiceDTO;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:52 PM
 */
public class InvoiceMapper {
    public static InvoiceDTO toDTO(Invoice invoice) {
        return InvoiceDTO.builder()
                .id(invoice.getId())
                .nameCustomer(invoice.getNameCustomer())
                .phone(invoice.getPhone())
                .totalOrder(invoice.getTotalOrder())
                .createDate(invoice.getCreateDate())
                .updateDate(invoice.getUpdateDate())
                .activeFlag(invoice.getActiveFlag())
                .member(MemberMapper.toDTO(invoice.getMember()))
                .invoiceDetails(invoice.getInvoiceDetails().stream().map(InvoiceDetailMapper::toDTO).collect(Collectors.toList()))
                .build();
    }
}
