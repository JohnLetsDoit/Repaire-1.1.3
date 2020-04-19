package com.tsw.repair.user.service;


import com.tsw.repair.user.model.User;
import com.tsw.repair.user.vo.SearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getUserInfoById(Long userId);

    List<User> getUserInfo();

    int addUserInfo(User user);

    int updateUserInfo(User user);

    int deleteUserInfo(Long id);

    List<User> searchUserInfo(SearchVo searchVo);
}
