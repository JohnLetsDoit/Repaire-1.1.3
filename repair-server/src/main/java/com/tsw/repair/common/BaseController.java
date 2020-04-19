package com.tsw.repair.common;

import com.tsw.repair.repairInfo.service.RepairInfoService;
import com.tsw.repair.role.model.Route;
import com.tsw.repair.role.service.RoleService;
import com.tsw.repair.role.service.RouteService;
import com.tsw.repair.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


/**
 * 道理同BaseService
 */
public class BaseController {

    @Autowired
    protected RepairInfoService repairInfoService;

    @Autowired
    protected RoleService roleService;

    @Autowired
    protected RouteService routeService;

    @Autowired
    protected UserService userService;
}
