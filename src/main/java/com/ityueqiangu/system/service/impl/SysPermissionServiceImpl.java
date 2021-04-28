package com.ityueqiangu.system.service.impl;

import com.ityueqiangu.system.domain.SysPermission;
import com.ityueqiangu.system.mapper.SysPermissionMapper;
import com.ityueqiangu.system.service.ISysPermissionService;
import com.ityueqiangu.core.util.StringUtils;
import com.ityueqiangu.core.enums.CommonEnum;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Clever、xia
 * @title: SysPermissionServiceImpl
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Service
public class SysPermissionServiceImpl implements ISysPermissionService{

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
     public List<SysPermission> selectSysPermissionList(SysPermission sysPermission) {
       return sysPermissionMapper.selectSysPermissionList(sysPermission);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysPermission selectSysPermissionById(Integer id) {
    	return sysPermissionMapper.selectSysPermissionById(id);
    }
	
    /**
     * 新增
     *
     * @param sysPermission 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysPermission(SysPermission sysPermission) {
    	return sysPermissionMapper.insertSysPermission(sysPermission);
    }
	
	
    /**
     * 修改
     *
     * @param sysPermission 修改的记录
     * @return 返回
     */
    public Integer updateSysPermission(SysPermission sysPermission) {
    	return sysPermissionMapper.updateSysPermission(sysPermission);
    }
	
  
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysPermissionById(Integer id) {
    	return sysPermissionMapper.deleteSysPermissionById(id);
    }
	
}