package com.ityueqiangu.project.system.service.impl;

import com.ityueqiangu.project.system.domain.SysResource;
import com.ityueqiangu.project.system.mapper.SysResourceMapper;
import com.ityueqiangu.project.system.service.ISysResourceService;
import com.ityueqiangu.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author FlowerStone
 * @title: SysResourceServiceImpl
 * @description:
 * @date 2021-11-14 10:38:35
 */
@Service
public class SysResourceServiceImpl implements ISysResourceService{

    @Autowired
    private SysResourceMapper sysResourceMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
     public List<SysResource> selectSysResourceList(SysResource sysResource) {
       return sysResourceMapper.selectSysResourceList(sysResource);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysResource selectSysResourceById(Integer id) {
    	return sysResourceMapper.selectSysResourceById(id);
    }
	
    /**
     * 新增
     *
     * @param sysResource 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysResource(SysResource sysResource) {
    	return sysResourceMapper.insertSysResource(sysResource);
    }
	
	
    /**
     * 修改
     *
     * @param sysResource 修改的记录
     * @return 返回
     */
    public Integer updateSysResource(SysResource sysResource) {
    	return sysResourceMapper.updateSysResource(sysResource);
    }
	
  
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysResourceById(Integer id) {
    	return sysResourceMapper.deleteSysResourceById(id);
    }
	
}