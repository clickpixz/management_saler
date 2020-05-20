package edu.mangement.service.Impl;

import edu.mangement.entity.Auth;
import edu.mangement.entity.Menu;
import edu.mangement.entity.Role;
import edu.mangement.mapper.MenuMapper;
import edu.mangement.mapper.RoleMapper;
import edu.mangement.model.MenuDTO;
import edu.mangement.model.RoleDTO;
import edu.mangement.repository.RoleRepository;
import edu.mangement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/19/2020
 * TIME : 1:01 AM
 */
@Transactional
@Component
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDTO getRoleById(RoleDTO roleDTO) {
        return Optional.ofNullable(roleDTO)
                .filter(r -> r.getId() != null)//neu id bang null tra ve rong
                .map(r -> roleRepository.findById(r.getId())//tim trong db neu co tra ve ko return null
                        .map(RoleMapper::toDTO)
                        .orElseGet(() -> null))
                .orElseGet(() -> null);
    }
}
