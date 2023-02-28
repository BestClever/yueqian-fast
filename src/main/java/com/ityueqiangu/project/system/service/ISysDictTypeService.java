package com.ityueqiangu.project.system.service;

import java.util.List;

import com.ityueqiangu.project.system.domain.SysDictData;
import com.ityueqiangu.project.system.domain.SysDictType;

/**
 * @author FlowerStone
 * @title: SysDictTypeService
 * @description:
 * @date 2022-03-21 17:06:28
 */
public interface ISysDictTypeService {

    /**
     * 查询所有记录
     *
     * @return list集合
     */
    List<SysDictType> selectSysDictTypeList(SysDictType sysDictType);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回SysDictType
     */
    SysDictType selectSysDictTypeById(Integer id);
	
    /**
     * 新增
     *
     * @param sysDictType 新增的记录
     * @return 返回ResultInfo
     */
    Integer insertSysDictType(SysDictType sysDictType);
	
	
    /**
     * 修改，修改所有字段
     *
     * @param sysDictType 修改的记录
     * @return 返回ResultInfo
     */
    Integer updateSysDictType(SysDictType sysDictType);
	
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    Integer deleteSysDictTypeById(Integer id);

}