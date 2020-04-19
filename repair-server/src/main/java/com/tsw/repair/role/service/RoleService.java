package com.tsw.repair.role.service;

import com.tsw.repair.role.model.Role;
import com.tsw.repair.role.vo.RoleVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    List<Role> getRoleList();

    Role getRoleById(Long id);

    int addRole(Role role);

    int updateRole(Role role);

    int deleteRole(Long id);
}
