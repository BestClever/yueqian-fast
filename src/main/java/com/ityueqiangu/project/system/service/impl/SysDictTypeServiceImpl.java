package com.ityueqiangu.project.system.service.impl;

import com.ityueqiangu.project.system.domain.SysDictData;
import com.ityueqiangu.project.system.domain.SysDictType;
import com.ityueqiangu.project.system.mapper.SysDictTypeMapper;
import com.ityueqiangu.project.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author FlowerStone
 * @title: SysDictTypeServiceImpl
 * @description:
 * @date 2022-03-21 17:06:28
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService{

    @Autowired
    private SysDictTypeMapper sysDictTypeMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
     public List<SysDictType> selectSysDictTypeList(SysDictType sysDictType) {
       return sysDictTypeMapper.selectSysDictTypeList(sysDictType);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysDictType selectSysDictTypeById(Integer id) {
    	return sysDictTypeMapper.selectSysDictTypeById(id);
    }
	
    /**
     * 新增
     *
     * @param sysDictType 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysDictType(SysDictType sysDictType) {
    	return sysDictTypeMapper.insertSysDictType(sysDictType);
    }
	
	
    /**
     * 修改
     *
     * @param sysDictType 修改的记录
     * @return 返回
     */
    public Integer updateSysDictType(SysDictType sysDictType) {
    	return sysDictTypeMapper.updateSysDictType(sysDictType);
    }
	
  
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysDictTypeById(Integer id) {
    	return sysDictTypeMapper.deleteSysDictTypeById(id);
    }

}