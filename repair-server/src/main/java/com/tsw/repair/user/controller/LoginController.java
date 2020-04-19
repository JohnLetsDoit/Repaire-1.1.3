package com.tsw.repair.user.controller;

import com.tsw.repair.constant.ResultCode;

import com.tsw.repair.result.Result;

import com.tsw.repair.user.model.User;
import com.tsw.repair.user.service.LoginService;
import com.tsw.repair.utils.GetTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public Result login(HttpServletResponse response , @RequestBody User user){
        System.out.println(user);
        User info  = loginService.login(user);
        System.out.println(info);
        if (info == null || info.equals("")){
            return Result.error(ResultCode.ERROR,"用户名或密码错误");
        }else {
            Result r  = new Result();
            String token = GetTokenUtil.getToken(info);
//            CookieUtil.setCookie(response,"token",token);
//            System.out.println(CookieUtil.getCookie());
            Map<String,Object> map = r.simple();
            map.put("token",token);
            map.put("user",user.toString());
            return Result.success(map);
        }
    }
}
