package edu.mangement.mapper;

import edu.mangement.entity.InvoiceDetail;
import edu.mangement.model.InvoiceDetailDTO;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 5:53 PM
 */
public class InvoiceDetailMapper{

    public static InvoiceDetailDTO toDTO(InvoiceDetail invoiceDetailEntity) {
        InvoiceDetailDTO invoiceDetailDTO = InvoiceDetailDTO.builder()
                .id(invoiceDetailEntity.getId())
                .quantity(invoiceDetailEntity.getQuantity())
                .unitPrice(invoiceDetailEntity.getUnitPrice())
                .createDate(invoiceDetailEntity.getCreateDate())
                .updateDate(invoiceDetailEntity.getUpdateDate())
                .activeFlag(invoiceDetailEntity.getActiveFlag())
                .build();
//        if(invoiceDetailEntity.getInvoice()!=null){
//            invoiceDetailDTO.setInvoice(InvoiceMapper.toDTO(invoiceDetailEntity.getInvoice()));
//        }
        if(invoiceDetailEntity.getItems()!=null){
            invoiceDetailDTO.setItems(ItemsMapper.toDTO(invoiceDetailEntity.getItems()));
        }
        return invoiceDetailDTO;
    }

    public static InvoiceDetail toEntity(InvoiceDetailDTO invoiceDetailDTO) {
        InvoiceDetail invoiceDetailEntity = InvoiceDetail.builder()
                .id(invoiceDetailDTO.getId())
                .quantity(invoiceDetailDTO.getQuantity())
                .unitPrice(invoiceDetailDTO.getUnitPrice())
                .createDate(invoiceDetailDTO.getCreateDate())
                .updateDate(invoiceDetailDTO.getUpdateDate())
                .activeFlag(invoiceDetailDTO.getActiveFlag())
                .build();
//        if(invoiceDetailDTO.getInvoice()!=null){
//            invoiceDetailEntity.setInvoice(InvoiceMapper.toEntity(invoiceDetailDTO.getInvoice()));
//        }
        if(invoiceDetailDTO.getItems()!=null){
            invoiceDetailEntity.setItems(ItemsMapper.toEntity(invoiceDetailDTO.getItems()));
        }
        return invoiceDetailEntity;
    }
}
