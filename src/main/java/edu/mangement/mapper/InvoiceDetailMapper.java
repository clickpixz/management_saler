package edu.mangement.mapper;

import edu.mangement.entity.InvoiceDetail;
import edu.mangement.model.dto.InvoiceDetailDTO;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:53 PM
 */
public class InvoiceDetailMapper{

    public static InvoiceDetailDTO toDTO(InvoiceDetail invoiceDetail) {
        return InvoiceDetailDTO.builder()
                .id(invoiceDetail.getId())
                .quantity(invoiceDetail.getQuantity())
                .unitPrice(invoiceDetail.getUnitPrice())
                .createDate(invoiceDetail.getCreateDate())
                .updateDate(invoiceDetail.getUpdateDate())
                .activeFlag(invoiceDetail.getActiveFlag())
                .invoice(InvoiceMapper.toDTO(invoiceDetail.getInvoice()))
                .items(ItemsMapper.toDTO(invoiceDetail.getItems()))
                .build();
    }

    public static InvoiceDetail toEntity(InvoiceDetailDTO invoiceDetail) {
        return InvoiceDetail.builder()
                .id(invoiceDetail.getId())
                .quantity(invoiceDetail.getQuantity())
                .unitPrice(invoiceDetail.getUnitPrice())
                .createDate(invoiceDetail.getCreateDate())
                .updateDate(invoiceDetail.getUpdateDate())
                .activeFlag(invoiceDetail.getActiveFlag())
                .invoice(InvoiceMapper.toEntity(invoiceDetail.getInvoice()))
                .items(ItemsMapper.toEntity(invoiceDetail.getItems()))
                .build();
    }
}
