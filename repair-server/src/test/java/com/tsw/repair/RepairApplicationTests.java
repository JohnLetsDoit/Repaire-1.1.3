package com.tsw.repair;

import com.tsw.repair.role.dao.MetaDao;
import com.tsw.repair.role.dao.RouteDao;
import com.tsw.repair.role.model.Meta;
import com.tsw.repair.role.model.Route;
import com.tsw.repair.role.service.RoleService;
import com.tsw.repair.role.vo.RoleVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepairApplicationTests  {
    @Autowired
    RouteDao routeDao;
    @Autowired
    MetaDao metaDao;
    @Autowired
    RoleService roleService;

    @Test
    public void contextLoads() {
//       RoleVo roleVo = roleService.getRoleById(1);
//        System.out.println(roleVo.toString());
    }


}
