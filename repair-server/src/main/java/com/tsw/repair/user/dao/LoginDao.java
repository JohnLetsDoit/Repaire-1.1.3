package com.tsw.repair.user.dao;


import com.tsw.repair.user.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {


    public User getUserInfoByPsw(User user);


}
