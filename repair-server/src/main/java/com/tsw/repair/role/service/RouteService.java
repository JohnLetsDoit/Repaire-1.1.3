package com.tsw.repair.role.service;

import com.tsw.repair.role.model.Route;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RouteService {

    List<Route> getRouteList();

    Route getRouteById(Long id);

    int addRoute(Route route);
}
