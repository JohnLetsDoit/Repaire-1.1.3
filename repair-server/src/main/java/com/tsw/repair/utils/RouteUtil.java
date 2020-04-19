package com.tsw.repair.utils;

import com.tsw.repair.common.BaseService;
import com.tsw.repair.role.dao.RouteDao;
import com.tsw.repair.role.model.Meta;
import com.tsw.repair.role.model.Route;
import com.tsw.repair.role.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RouteUtil {

    /**
     * meta_title 和 meta_icon字段转化为Meta类型
     * 此为单个路由转换
     * @param routes
     * @return
     */
    public static List<Route> toMeta(List<Route> routes){
        for (Route route :routes){
            toMetaOne(route);
        }
        return routes;
    }

    /**
     * meta_title 和 meta_icon字段转化为Meta类型
     * 此为单个路由转换
     * @param route
     * @return
     */
    public static Route toMetaOne(Route route){
        Meta meta = new Meta();
        meta.setTitle(route.getMetaTitle());
        meta.setIcon(route.getMetaIcon());
        route.setMeta(meta);
        return route;
    }

    /**
     * 判断是否有子路由
     * @param route
     * @return
     */
    public static Boolean isHaveCid(Route route){
        if (route.getCid() != null && !"".equals(route.getCid().trim()) && route.getCid().trim().length()>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取子路由，返回childRoutes数组，包含所有的子路由
     * @param cids
     * @param routeDao
     * @return
     */
    public static List<Route> getChildrensByRouteCid(Long[] cids,RouteDao routeDao){
        List<Route> childRoutes = new ArrayList<>();
        for (Long oCid : cids){
            if (routeDao.getRouteById(oCid) != null){
                Route childRoute = toMetaOne(routeDao.getRouteById(oCid));
                childRoutes.add(childRoute);
            }
        }
        return childRoutes;
    }

    public static Route routeAddChildren(Route route,RouteDao routeDao){
        if (RouteUtil.isHaveCid(route)){
            Long[] cids = TransferArrUtil.stringToLong(route.getCid().split(","));
            List<Route> childRoutes = RouteUtil.getChildrensByRouteCid(cids,routeDao);
            route.setChildren(childRoutes);
        }
        return route;
    }
}
