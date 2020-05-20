package edu.mangement.mapper;

import edu.mangement.model.MemberDTO;
import edu.mangement.entity.Member;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/18/2020
 * TIME : 1:48 AM
 */
public class MemberMapper{
    public static MemberDTO toDTO(Member member) {
        MemberDTO memberDTO = MemberDTO.builder()
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
                .build();
        if(member.getBranch()!=null){
            memberDTO.setBranch(BranchMapper.toDTO(member.getBranch()));
        }
        if(member.getDateWorks()!=null){
            memberDTO.setDateWorks(member.getDateWorks().stream().map(DateWorkMapper::toDTO).collect(Collectors.toList()));
        }
        if(member.getRole()!=null){
            memberDTO.setRole(RoleMapper.toDTO(member.getRole()));
        }
//        if(member.getInvoices()!=null){
//            memberDTO.setInvoices(member.getInvoices().stream().map(InvoiceMapper::toDTO).collect(Collectors.toList()));
//        }
//        if(member.getInventoryHistories()!=null){
//            memberDTO.setInventoryHistories(member.getInventoryHistories().stream().map(InventoryHistoryMapper::toDTO).collect(Collectors.toList()));
//        }
        return memberDTO;
    }

    public static Member toEntity(MemberDTO member) {
        Member memberEntity = Member.builder()
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
                .build();
        if(member.getBranch()!=null){
            memberEntity.setBranch(BranchMapper.toEntity(member.getBranch()));
        }
        if(member.getRole()!=null){
            memberEntity.setRole(RoleMapper.toEntity(member.getRole()));
        }
        if(member.getDateWorks()!=null){
            memberEntity.setDateWorks(member.getDateWorks().stream().map(DateWorkMapper::toEntity).collect(Collectors.toList()));
        }
        return memberEntity;
    }
}
