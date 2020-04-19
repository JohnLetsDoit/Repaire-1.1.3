package com.tsw.repair.role;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsw.repair.common.BaseController;
import com.tsw.repair.common.BaseService;
import com.tsw.repair.repairInfo.model.RepairInfo;
import com.tsw.repair.result.Result;
import com.tsw.repair.role.model.Role;
import com.tsw.repair.role.model.Route;
import com.tsw.repair.role.service.RouteService;
import com.tsw.repair.utils.TransferArrUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController extends BaseController {

    @GetMapping("/getRouteList")
    public Result getRouteList(){
        List<Route> routes = routeService.getRouteList();
        return Result.success(routes);
    }

    @GetMapping("/getRouteListWithPage")
    public Result getRouteListWithPage(@RequestParam int pageNum, @RequestParam int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Route> routes = routeService.getRouteList();
        PageInfo<Route> pageInfo = new PageInfo<>(routes);
        return Result.success(pageInfo);
    }

    @GetMapping("/getRoute")
    public Result getRouteById(Long id){
        Route route = routeService.getRouteById(id);
        return Result.success(route);
    }

    public Boolean containId(Long[] ids,Long id){
        for (Long oId : ids){
            if (oId == id){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取应该显示的路由列表，传入的参数是roleIds（用户所具有角色的id号，连接成字符串，用"，"连接
     * @param roleIds
     * @return
     */
    @GetMapping("/getRouteByRole")
    public Result getRouteByRole(@RequestParam String roleIds){

        String[] sRoles = roleIds.split(",");
        Long[] roles = TransferArrUtil.stringToLong(sRoles);
        List<Route> routes = new ArrayList<>();

        for (Long roleId : roles){
           routes.addAll(roleService.getRoleById(roleId).getRoutes());
        }

        Long[] routeIds = new Long[routes.size()];
        for (int i=0;i<routes.size();i++){
            routeIds[i] = routes.get(i).getRouteId();
        }

        List<Route> routesAll = routeService.getRouteList();
        Iterator<Route> routeIterator = routesAll.iterator();
        while (routeIterator.hasNext()){
                Route route = routeIterator.next();
                if (route.getIsChild()){
                    if (!containId(routeIds,route.getRouteId())){
                        routeIterator.remove();
                    }
                }else {
                    Iterator<Route> routeIteratorChild = route.getChildren().iterator();
                    while (routeIteratorChild.hasNext()){
                        Route childRoute = routeIteratorChild.next();
                        if (!containId(routeIds,childRoute.getRouteId())){
                            routeIteratorChild.remove();
                        }
                    }

                }
        }
        System.out.println(routesAll);
        return Result.success(routesAll);
    }
}
