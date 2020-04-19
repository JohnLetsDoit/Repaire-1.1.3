package com.tsw.repair.common;

import com.tsw.repair.repairInfo.dao.RepairInfoDao;
import com.tsw.repair.role.dao.MetaDao;
import com.tsw.repair.role.dao.RoleDao;
import com.tsw.repair.role.dao.RoleRouteDao;
import com.tsw.repair.role.dao.RouteDao;
import com.tsw.repair.user.dao.LoginDao;
import com.tsw.repair.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * BaseService 含有所有dao层的类
 * 用来service层继承
 */
@Service
public class BaseService {

        @Resource
        protected LoginDao loginDao;

        @Resource
        protected RoleDao roleDao;

        @Resource
        protected RouteDao routeDao;

        @Resource
        protected UserDao userDao;

        @Resource
        protected RoleRouteDao roleRouteDao;

        @Resource
        protected RepairInfoDao repairInfoDao;


    }
