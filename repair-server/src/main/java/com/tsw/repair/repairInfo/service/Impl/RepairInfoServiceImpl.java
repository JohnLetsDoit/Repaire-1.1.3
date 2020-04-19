package com.tsw.repair.repairInfo.service.Impl;


import com.tsw.repair.common.BaseService;
import com.tsw.repair.faultInfo.model.FaultInfo;
import com.tsw.repair.repairInfo.dao.RepairInfoDao;
import com.tsw.repair.repairInfo.model.RepairInfo;
import com.tsw.repair.repairInfo.service.RepairInfoService;
import com.tsw.repair.repairInfo.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RepairInfoServiceImpl extends BaseService implements RepairInfoService {


    @Override
    public List<RepairInfo> getRepairInfo() {
        return repairInfoDao.getRepairInfo();
    }

    @Override
    public int updateStateByRepairId(int state, Long repairId) {
        return repairInfoDao.updateStateByRepairId(state,repairId);
    }

    @Override
    public int updateRepairInfo(RepairInfo repairInfo) {
        repairInfo.setRepairTime(new Date());
        return repairInfoDao.updateRepairInfo(repairInfo);
    }

    @Override
    public List<RepairInfo> getRepairInfoBySearch(SearchVo searchVo) {
        return repairInfoDao.getRepairInfoBySearch(searchVo);
    }

    @Override
    public int addRepairInfo(RepairInfo repairInfo) {
        repairInfo.setCreateTime(new Date());
        return repairInfoDao.addRepairInfo(repairInfo);
    }

    @Override
    public int deleteRepairInfo(Long repairId) {
        return repairInfoDao.deleteRepairInfo(repairId);
    }


}
