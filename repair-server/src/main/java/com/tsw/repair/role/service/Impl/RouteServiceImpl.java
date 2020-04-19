package com.tsw.repair.role.service.Impl;

import com.tsw.repair.common.BaseService;
import com.tsw.repair.role.dao.MetaDao;
import com.tsw.repair.role.dao.RoleDao;
import com.tsw.repair.role.dao.RouteDao;
import com.tsw.repair.role.model.Meta;
import com.tsw.repair.role.model.Role;
import com.tsw.repair.role.model.Route;
import com.tsw.repair.role.service.RouteService;
import com.tsw.repair.utils.RouteUtil;
import com.tsw.repair.utils.TransferArrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteServiceImpl extends BaseService implements RouteService {

    @Override
    public List<Route> getRouteList() {
        List<Route> routes = RouteUtil.toMeta(routeDao.getRouteList());
        for (Route route : routes){
            RouteUtil.routeAddChildren(route,routeDao);
        }
        return routes;
    }

    @Override
    public Route getRouteById(Long id) {
        Route route =RouteUtil.toMetaOne(routeDao.getRouteById(id));
        RouteUtil.routeAddChildren(route,routeDao);
        return route;
    }

    @Override
    public int addRoute(Route route) {
        return 0;
    }

}
