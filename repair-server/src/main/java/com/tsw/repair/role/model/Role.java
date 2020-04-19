package com.tsw.repair.role.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role {

    private Long roleId;
    private String roleName;
    private String description;
    private List<Route> routes;

}
