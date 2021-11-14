package com.ityueqiangu.project.system.service.impl;

import com.ityueqiangu.project.system.domain.SysRoleResource;
import com.ityueqiangu.project.system.mapper.SysRoleResourceMapper;
import com.ityueqiangu.project.system.service.ISysRoleResourceService;
import com.ityueqiangu.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author FlowerStone
 * @title: SysRoleResourceServiceImpl
 * @description:
 * @date 2021-11-14 07:11:02
 */
@Service
public class SysRoleResourceServiceImpl implements ISysRoleResourceService{

    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
     public List<SysRoleResource> selectSysRoleResourceList(SysRoleResource sysRoleResource) {
       return sysRoleResourceMapper.selectSysRoleResourceList(sysRoleResource);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysRoleResource selectSysRoleResourceById(Integer id) {
    	return sysRoleResourceMapper.selectSysRoleResourceById(id);
    }
	
    /**
     * 新增
     *
     * @param sysRoleResource 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysRoleResource(SysRoleResource sysRoleResource) {
    	return sysRoleResourceMapper.insertSysRoleResource(sysRoleResource);
    }
	
	
    /**
     * 修改
     *
     * @param sysRoleResource 修改的记录
     * @return 返回
     */
    public Integer updateSysRoleResource(SysRoleResource sysRoleResource) {
    	return sysRoleResourceMapper.updateSysRoleResource(sysRoleResource);
    }
	
  
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysRoleResourceById(Integer id) {
    	return sysRoleResourceMapper.deleteSysRoleResourceById(id);
    }
	
}