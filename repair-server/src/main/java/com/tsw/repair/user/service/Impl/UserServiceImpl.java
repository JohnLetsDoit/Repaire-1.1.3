package com.tsw.repair.user.service.Impl;


import com.tsw.repair.user.dao.UserDao;
import com.tsw.repair.user.model.User;
import com.tsw.repair.user.service.UserService;
import com.tsw.repair.user.vo.SearchVo;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public User getUserInfoById(Long userId) {
        User user = userDao.getUserInfoById(userId);
        return user;
    }

    @Override
    public List<User> getUserInfo() {
        List<User> users = userDao.getUserInfo();
        return users;
    }

    @Override
    public int addUserInfo(User user) {
        return 0;
    }

    @Override
    public int updateUserInfo(User user) {
        return 0;
    }

    @Override
    public int deleteUserInfo(Long id) {
        return 0;
    }

    @Override
    public List<User> searchUserInfo(SearchVo searchVo) {
        return userDao.searchUserInfo(searchVo);
    }
}
