package com.tsw.repair.contact;

import com.tsw.repair.role.dao.RoleRouteDao;
import com.tsw.repair.role.model.RoleRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class contactTest {
    @Resource
    RoleRouteDao dao;

    @Test
    public void mainTest(){
        RoleRoute roleRoute = new RoleRoute();
        roleRoute.setRoleId(Long.valueOf(1));
        roleRoute.setRouteId(Long.valueOf(5));
        RoleRoute roleRoute2 = new RoleRoute();
        roleRoute2.setRoleId(Long.valueOf(1));
        roleRoute2.setRouteId(Long.valueOf(4));
        RoleRoute roleRoute3 = new RoleRoute();
        roleRoute3.setRoleId(Long.valueOf(1));
        roleRoute3.setRouteId(Long.valueOf(7));
        List<RoleRoute> roleRoutes = new ArrayList<>();
        roleRoutes.add(roleRoute);
        roleRoutes.add(roleRoute2);
        roleRoutes.add(roleRoute3);
//        int contact  = dao.addRoutesByRoleId(roleRoutes);

    }

    @Test
    public void test(){

    }
}
