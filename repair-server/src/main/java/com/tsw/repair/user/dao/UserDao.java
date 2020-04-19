package com.tsw.repair.user.dao;


import com.tsw.repair.user.model.User;
import com.tsw.repair.user.vo.SearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

     User getUserInfoById(@Param("userId") Long userId);

     List<User> getUserInfo();

     int addUserInfo(User user);

     int updateUserInfo(User user);

     int deleteUserInfo(Long id);

     /**
      * 查询
      * @param searchVo
      * @return
      */
     List<User> searchUserInfo(SearchVo searchVo);
}
