package com.tsw.repair.role.dao;


import com.tsw.repair.role.model.Route;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RouteDao {

    List<Route> getRouteList();

    Route getRouteById(Long routeId);

    int addRoute(Route route);

    int deleteRoute(Long routeId);

    int updateRoute(Route route);
}
