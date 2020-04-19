package com.tsw.repair.repairInfo.model;

import com.tsw.repair.faultInfo.model.FaultInfo;
import com.tsw.repair.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zx
 * 2019年7月19日
 */
@Data
public class RepairInfo {

    private Long repairId;
    /**
     *  报修人员相关信息
     */
    private String number;
    private String repairName;
    private String repairAddress;
    private String repairRoom;
    private String phone;
    private Long faultId;
    private FaultInfo faultInfo;
    private String faultDesc;
    private String description;
    private String orderTime;

    /**
     *
     *  操作人员相关信息
     */
    private Long recordUserId ;
    private User recordUser;
    private Long serviceUserId;
    private User serviceUser;

    private String remark;//操作备注
    private int state;//当前操作状态
    private int category;//种类
    /**
     *  时间类
     */
    private Date createTime;
    private Date repairTime;

}
