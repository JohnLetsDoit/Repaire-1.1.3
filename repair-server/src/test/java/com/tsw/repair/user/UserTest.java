package com.tsw.repair.user;

import com.tsw.repair.user.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
    @Resource
    UserDao userDao;

    @Test
    public void mainTest(){
        System.out.println(userDao.getUserInfoById(Long.valueOf(1)));

    }
}
