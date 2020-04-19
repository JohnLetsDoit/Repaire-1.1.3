package com.tsw.repair.faultInfo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * 故障类表
 */
@Data
public class FaultInfo {

    private int id;
    private String faultName;
    private String faultDesc;
    
}
