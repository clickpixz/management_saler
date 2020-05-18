package edu.mangement.mapper.model;

import edu.mangement.model.dto.MemberDTO;
import edu.mangement.entity.Member;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 1:48 AM
 */
public class MemberMapper{
    public static MemberDTO toDTO(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .username(member.getUsername())
                .password(member.getPassword())
                .image(member.getImage())
                .doB(member.getDoB())
                .sex(member.getSex())
                .salary(member.getSalary())
                .createDate(member.getCreateDate())
                .updateDate(member.getUpdateDate())
                .activeFlag(member.getActiveFlag())
                .branch(BranchMapper.toDTO(member.getBranch()))
                .role(RoleMapper.toDTO(member.getRole()))
                .dateWorks(member.getDateWorks().stream().map(DateWorkMapper::toDTO).collect(Collectors.toList()))
                .invoices(member.getInvoices().stream().map(InvoiceMapper::toDTO).collect(Collectors.toList()))
                .inventoryHistories(member.getInventoryHistories().stream().map(InventoryHistoryMapper::toDTO).collect(Collectors.toList()))
                .build();
    }
}
