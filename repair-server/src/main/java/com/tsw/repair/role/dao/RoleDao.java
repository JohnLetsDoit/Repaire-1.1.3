package com.tsw.repair.role.dao;

import com.tsw.repair.role.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {

    int addRole(Role role);

    Role getRoleById(Long id);

    int updateRole(Role role);

    List<Role> getRoleList();

    int deleteRole(Long id);

    List<Role> getRoutesByRole();

    Role getRoutesByRoleId(Long roleId);
}
