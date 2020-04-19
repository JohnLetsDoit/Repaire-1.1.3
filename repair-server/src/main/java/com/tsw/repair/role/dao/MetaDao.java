package com.tsw.repair.role.dao;

import com.tsw.repair.role.model.Meta;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MetaDao {

    List<Meta> getMetaList();

    Meta getMetaById(Long id);

    int addMeta(Meta meta);

    int updateMeta(Meta meta);

    int deleteMeta(Long id);

}
