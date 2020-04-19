package com.tsw.repair.role.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Route {

    private Long routeId;
    private String path;
    private String component;
    private String routeName;
    private Meta meta;
    @JsonIgnore
    private String metaTitle;
    @JsonIgnore
    private String metaIcon;
    private String redirect;
    @JsonIgnore
    private String cid;
    private List<Route> children;
    private Boolean hidden;
    private Boolean alwaysShow;
    private Boolean isChild;

}
