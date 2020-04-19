package com.tsw.repair.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static String setCookie(HttpServletResponse response , String cookieName, String cookieContent){
        Cookie cookie = new Cookie(cookieName,cookieContent);
        cookie.setMaxAge(60000);
        cookie.setPath("/");
//        cookie.setDomain("");
        response.addCookie(cookie);
        return "cookie设置成功";
    }

    public static String getCookie(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(cookieName)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
