package com.tsw.repair.repairInfo.dao;



import com.tsw.repair.faultInfo.model.FaultInfo;
import com.tsw.repair.repairInfo.model.RepairInfo;
import com.tsw.repair.repairInfo.vo.SearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RepairInfoDao {
    /**
     * 添加报修记录
     * @param repairInfo
     * @return
     */
    boolean insertRepairInfo(RepairInfo repairInfo);

    /**
     * 查询所有的报修信息
     * @return
     */

    public List<RepairInfo> selectAllInfo();

    public FaultInfo selectFaultNameById(int id);

    List<RepairInfo> getRepairInfo();

    List<RepairInfo> getRepairInfoBySearch(SearchVo searchVo);

    int updateStateByRepairId(@Param("state") int state, @Param("repairId") Long repairId);

    int updateRepairInfo(RepairInfo repairInfo);

    int addRepairInfo(RepairInfo repairInfo);

    int deleteRepairInfo(Long repairId);
}
