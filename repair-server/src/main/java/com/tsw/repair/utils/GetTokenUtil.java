package com.tsw.repair.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tsw.repair.user.model.User;


import java.util.Date;

public class GetTokenUtil {

    public static String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(String.valueOf(user.getUserId()))
                .withExpiresAt(new Date((System.currentTimeMillis()+600000)))
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
