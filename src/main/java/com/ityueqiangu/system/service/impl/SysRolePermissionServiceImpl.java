package com.ityueqiangu.system.service.impl;

import com.ityueqiangu.system.domain.SysRolePermission;
import com.ityueqiangu.system.mapper.SysRolePermissionMapper;
import com.ityueqiangu.system.service.ISysRolePermissionService;
import com.ityueqiangu.core.util.StringUtils;
import com.ityueqiangu.core.enums.CommonEnum;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Clever、xia
 * @title: SysRolePermissionServiceImpl
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Service
public class SysRolePermissionServiceImpl implements ISysRolePermissionService{

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
     public List<SysRolePermission> selectSysRolePermissionList(SysRolePermission sysRolePermission) {
       return sysRolePermissionMapper.selectSysRolePermissionList(sysRolePermission);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysRolePermission selectSysRolePermissionById(Integer id) {
    	return sysRolePermissionMapper.selectSysRolePermissionById(id);
    }
	
    /**
     * 新增
     *
     * @param sysRolePermission 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysRolePermission(SysRolePermission sysRolePermission) {
    	return sysRolePermissionMapper.insertSysRolePermission(sysRolePermission);
    }
	
	
    /**
     * 修改
     *
     * @param sysRolePermission 修改的记录
     * @return 返回
     */
    public Integer updateSysRolePermission(SysRolePermission sysRolePermission) {
    	return sysRolePermissionMapper.updateSysRolePermission(sysRolePermission);
    }
	
  
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysRolePermissionById(Integer id) {
    	return sysRolePermissionMapper.deleteSysRolePermissionById(id);
    }
	
}