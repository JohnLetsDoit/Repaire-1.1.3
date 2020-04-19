package com.tsw.repair.role.service.Impl;

import com.tsw.repair.common.BaseService;
import com.tsw.repair.role.dao.MetaDao;
import com.tsw.repair.role.dao.RoleDao;
import com.tsw.repair.role.dao.RouteDao;
import com.tsw.repair.role.model.Meta;
import com.tsw.repair.role.model.Role;
import com.tsw.repair.role.model.RoleRoute;
import com.tsw.repair.role.model.Route;
import com.tsw.repair.role.service.RoleService;
import com.tsw.repair.role.service.RouteService;
import com.tsw.repair.role.vo.RoleVo;
import com.tsw.repair.role.vo.RouteVo;
import com.tsw.repair.utils.TransferArrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.rowset.spi.TransactionalWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {

    @Autowired
    RouteService routeService;

    @Override
    public List<Role> getRoleList() {
        List<Role> roles = roleDao.getRoutesByRole();
        return roles;
    }

    @Override
    public Role getRoleById(Long id) {
        Role role = roleDao.getRoutesByRoleId(id);
        return role;
    }

    @Override
    public int addRole(Role role){
        roleDao.addRole(role);
        List<RoleRoute> roleRoutes = RoleAndRouteToRoleRoute(role);
        int addRole = roleRouteDao.addRoleRoutes(roleRoutes);
        return addRole;
    }

    /**
     * Role对象和Route对象提取id，转化为RoleRoute对象
     * @param role
     * @return
     */
    public List<RoleRoute> RoleAndRouteToRoleRoute(Role role){
        List<RoleRoute> roleRoutes = new ArrayList<>();
        for (int i=0; i<role.getRoutes().size(); i++){
            if (role.getRoutes().get(i).getIsChild()){
                RoleRoute roleRoute= new RoleRoute();
                roleRoute.setRoleId(role.getRoleId());
                roleRoute.setRouteId(role.getRoutes().get(i).getRouteId());
                roleRoutes.add(roleRoute);
            }
        }
        return roleRoutes;
    }

    /**
     * 更新角色，需要判断更新允许通过的路由是空还是有改变
     * @param role
     * @return
     */
    @Override
    public int updateRole(Role role){
        //个人信息的更新
        roleDao.updateRole(role);
        //role_route_contact表的更新
        if (role.getRoutes().size()>0 && role.getRoutes() != null){
            List<RoleRoute> roleRoutes = RoleAndRouteToRoleRoute(role);
            roleRouteDao.deleteRoleById(role.getRoleId());
            int addRole = roleRouteDao.addRoleRoutes(roleRoutes);
            return addRole;
        }else {
            return roleRouteDao.deleteRoleById(role.getRoleId());
        }
    }

    @Override
    public int deleteRole(Long id){
        roleDao.deleteRole(id);
        int deleteRole = roleRouteDao.deleteRoleById(id);
        return deleteRole;
    }
}
