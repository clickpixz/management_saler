package edu.mangement.service;

import edu.mangement.entity.Menu;
import edu.mangement.model.MenuDTO;
import edu.mangement.model.RoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * USER : ctc
 * DATE : 5/19/2020
 * TIME : 12:58 AM
 */
@Service
public interface RoleService {
    RoleDTO getRoleById(RoleDTO roleDTO);
}