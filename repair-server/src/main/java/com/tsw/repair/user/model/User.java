package com.tsw.repair.user.model;


import com.tsw.repair.role.model.Role;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author zx
 * 2019年6月9日
 */
@Data
public class User {
    private Long userId;
    private String name;//姓名
    private String account;//账号
    private String password;//密码
    private String avatar;//头像
    private String description;//备注
    private Date createTime;
    private List<Role> roles;
}
