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
import java.util.Comparator;
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
    public RoleDTO findRoleById(Long roleId) {
        return Optional.ofNullable(roleId)
                .map(id->roleRepository.findRoleByIdAndActiveFlag(id,1))
                .map(RoleMapper::toDTO)
                .orElseGet(() -> null);
    }

    @Override
    public List<RoleDTO> findAllRole() {
        return roleRepository.findAllByActiveFlag(1,null)
                                .stream()
                                .map(RoleMapper::toDTO)
                                .sorted(Comparator.comparingLong(RoleDTO::getId))
                                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO saveRole(RoleDTO roleDTO) throws Exception {
        return null;
    }

    @Override
    public void deleteRole(RoleDTO roleDTO) throws Exception {

    }
}
