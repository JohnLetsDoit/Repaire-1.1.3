package com.tsw.repair.role.dao;

import com.tsw.repair.role.model.RoleRoute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleRouteDao {
    /**
     * 添加Role和Routes
     * @param roleRoutes
     * @return
     */
    int addRoleRoutes(@Param("roleRoutes") List<RoleRoute> roleRoutes);

    int addRoleRoute(RoleRoute roleRoute);

    int deleteRoleById(Long roleId);
}
