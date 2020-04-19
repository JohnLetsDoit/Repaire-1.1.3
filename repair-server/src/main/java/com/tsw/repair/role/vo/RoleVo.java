package com.tsw.repair.role.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zx
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo {

    private int id;
    private String roleName;
    private RouteVo routesVo;
}
