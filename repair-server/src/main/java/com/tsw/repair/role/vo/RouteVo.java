package com.tsw.repair.role.vo;

import com.tsw.repair.role.model.Meta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author zx
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteVo {
    private Long id;
    private String path;
    private String component;
    private String name;
    private Meta meta;
    private String redirect;
    private List<RouteVo> children;
}
