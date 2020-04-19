package com.tsw.repair.user.controller;

import com.auth0.jwt.JWT;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tsw.repair.anno.LoginToken;

import com.tsw.repair.common.BaseController;
import com.tsw.repair.role.model.Route;
import com.tsw.repair.user.vo.SearchVo;
import com.tsw.repair.result.Result;
import com.tsw.repair.user.dao.UserDao;
import com.tsw.repair.user.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {


    @Autowired
    HttpServletRequest request;

    @GetMapping("/info")
    @LoginToken
    public Result getInfo() {
        String token = request.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        Long id = Long.valueOf(userId);
        User user = userService.getUserInfoById(id);
        return Result.success(user);
    }

    @GetMapping("/info/all")
    @LoginToken
    public Result getInfoAll() {
        List<User> users = userService.getUserInfo();
        return Result.success(users);

    }

    /**
     * user类 搜索
     * @param searchVo
     * @return
     */
    @PostMapping("/search")
    public Result searchUserInfo(@RequestBody SearchVo searchVo){
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        List<User> users = userService.searchUserInfo(searchVo);
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        return Result.success(userPageInfo);
    }
    @PostMapping("/add")
    @LoginToken
    public Result addUser(@RequestBody User user){
//        String roles = "";
//        for (String role : user.getRoles()){
//            roles = roles +role +",";
//        }
//        user.setRole(roles);
        return Result.success();
    }

    @PostMapping("/del")
    @LoginToken
    public Result delUser(@Param("id") int id){
        return Result.success();
    }
}