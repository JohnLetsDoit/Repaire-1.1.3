package com.tsw.repair.route;

import com.tsw.repair.role.dao.RouteDao;
import com.tsw.repair.role.model.Route;
import com.tsw.repair.user.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.management.relation.Role;

@RunWith(SpringRunner.class)
@SpringBootTest
public class routeTest {

    @Resource
    RouteDao routeDao;

    @Test
    public void mainTest(){
        Route route = new Route();
        route.setRouteName("test");
        route.setPath("/test");
        route.setRedirect("/retest");
        route.setComponent("/component");
        route.setHidden(true);

        int addRouteTest =  routeDao.addRoute(route);
        Assert.assertEquals(1,addRouteTest);

        Route getRouteByIdTest = routeDao.getRouteById(route.getRouteId());
        Assert.assertNotNull(getRouteByIdTest);
        Assert.assertEquals(route.getRouteName(),getRouteByIdTest.getRouteName());

        route.setRouteName("testUpdate");
        int updateRouteTest = routeDao.updateRoute(route);
        Assert.assertEquals(1,updateRouteTest);
        Route getRouteByIdUpdateTest = routeDao.getRouteById(route.getRouteId());
        Assert.assertEquals(getRouteByIdUpdateTest.getRouteName(),route.getRouteName());

        int deleteRouterTest = routeDao.deleteRoute(route.getRouteId());
        Assert.assertEquals(1,deleteRouterTest);
        Route deleteRoute = routeDao.getRouteById(route.getRouteId());
        Assert.assertNull(deleteRoute);

    }
}
