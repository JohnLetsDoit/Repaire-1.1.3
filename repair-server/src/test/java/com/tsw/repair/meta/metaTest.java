package com.tsw.repair.meta;

import com.tsw.repair.role.dao.MetaDao;
import com.tsw.repair.role.model.Meta;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class metaTest {

    @Resource
    MetaDao metaDao;

    @Test
    public void mainTest(){
//        Meta meta = new Meta();
//        meta.setTitle("metaTest");
//        meta.setIcon("/icon");
//
//
//        int addMetaTest = metaDao.addMeta(meta);
//        Assert.assertEquals(1,addMetaTest);
//
//        Meta metaInfo = metaDao.getMetaById(meta.());
//        Assert.assertNotNull(metaInfo);
//        Assert.assertEquals(metaInfo.getTitle(),meta.getTitle());
//
//        meta.setTitle("updateTest");
//        int updateMetaTest = metaDao.updateMeta(meta);
//        Assert.assertEquals(1,updateMetaTest);
//        Assert.assertNotEquals(meta.getTitle(),metaInfo.getTitle());
//
//        int deleteMetaTest = metaDao.deleteMeta(meta.getId());
//        Assert.assertEquals(1,deleteMetaTest);
//        metaInfo = metaDao.getMetaById(meta.getId());
//        Assert.assertNull(metaInfo);

    }
}
