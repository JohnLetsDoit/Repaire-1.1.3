package com.tsw.repair.repairInfo.vo;

import lombok.Data;

@Data
public class SearchVo {
    private int state;
    private String content;
    private String orderTime;
    private int pageSize;
    private int pageNum;

}
