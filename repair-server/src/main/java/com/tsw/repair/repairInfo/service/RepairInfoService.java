package com.tsw.repair.repairInfo.service;

//import com.tsw.repair.entity.RepairInfo;
import com.tsw.repair.repairInfo.model.RepairInfo;
import com.tsw.repair.repairInfo.vo.SearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RepairInfoService {

    List<RepairInfo> getRepairInfo();

    int updateStateByRepairId(int state,Long repairId);

    int updateRepairInfo(RepairInfo repairInfo);

    List<RepairInfo> getRepairInfoBySearch(SearchVo searchVo);

    int addRepairInfo(RepairInfo repairInfo);

    int deleteRepairInfo(Long repairId);

}
