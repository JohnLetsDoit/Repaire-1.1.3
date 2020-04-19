package com.tsw.repair.repair;

import com.tsw.repair.repairInfo.dao.RepairInfoDao;
import com.tsw.repair.repairInfo.model.RepairInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepairInfoTest {
    @Resource
    RepairInfoDao repairInfoDao;

    @Test
    public void MainTest(){
        RepairInfo repairInfo = new RepairInfo();
        repairInfo.setNumber("160709014");

        int add = repairInfoDao.addRepairInfo(repairInfo);
        Assert.assertNotEquals(0,add);
    }
}
