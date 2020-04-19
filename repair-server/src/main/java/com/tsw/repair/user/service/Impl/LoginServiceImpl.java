package com.tsw.repair.user.service.Impl;

import com.tsw.repair.user.dao.LoginDao;
import com.tsw.repair.user.model.User;
import com.tsw.repair.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginDao loginDao;

    public User login(User user){
        return loginDao.getUserInfoByPsw(user);
    }
}
