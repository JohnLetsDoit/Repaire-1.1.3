package com.tsw.repair.role;

import com.tsw.repair.common.BaseController;
import com.tsw.repair.result.Result;
import com.tsw.repair.role.dao.MetaDao;
import com.tsw.repair.role.dao.RouteDao;
import com.tsw.repair.role.model.Meta;
import com.tsw.repair.role.model.Role;
import com.tsw.repair.role.model.Route;
import com.tsw.repair.role.service.RoleService;
import com.tsw.repair.role.vo.RoleVo;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @GetMapping("/getRole")
    public Result getRoleById(@RequestParam Long id){
        Role role = roleService.getRoleById(id);
        return Result.success(role);
    }

    @GetMapping("/getRoleList")
    public Result getRoleList(){
        List<Role> roles = roleService.getRoleList();
        return Result.success(roles);
    }

    @PostMapping("/addRole")
    public Result addRole(@RequestBody Role role){
        int addRole =  roleService.addRole(role);
        return Result.success(role);
    }

    @PutMapping("/updateRole/{id}")
    public Result updateRole(@PathVariable("id") Long id, @RequestBody Role role){
        role.setRoleId(id);
        int updateRole = roleService.updateRole(role);
        return Result.success(role);
    }

    @DeleteMapping("/deleteRole/{id}")
    public Result deleteRole(@PathVariable("id") Long id){
        int deleteRole = roleService.deleteRole(id);
        return Result.success();
    }
}
