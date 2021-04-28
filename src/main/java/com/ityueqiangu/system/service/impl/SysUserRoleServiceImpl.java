package com.ityueqiangu.system.service.impl;

import com.ityueqiangu.system.domain.SysUserRole;
import com.ityueqiangu.system.mapper.SysUserRoleMapper;
import com.ityueqiangu.system.service.ISysUserRoleService;
import com.ityueqiangu.core.util.StringUtils;
import com.ityueqiangu.core.enums.CommonEnum;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Clever、xia
 * @title: SysUserRoleServiceImpl
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Service
public class SysUserRoleServiceImpl implements ISysUserRoleService{

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
     public List<SysUserRole> selectSysUserRoleList(SysUserRole sysUserRole) {
       return sysUserRoleMapper.selectSysUserRoleList(sysUserRole);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysUserRole selectSysUserRoleById(Integer id) {
    	return sysUserRoleMapper.selectSysUserRoleById(id);
    }
	
    /**
     * 新增
     *
     * @param sysUserRole 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysUserRole(SysUserRole sysUserRole) {
    	return sysUserRoleMapper.insertSysUserRole(sysUserRole);
    }
	
	
    /**
     * 修改
     *
     * @param sysUserRole 修改的记录
     * @return 返回
     */
    public Integer updateSysUserRole(SysUserRole sysUserRole) {
    	return sysUserRoleMapper.updateSysUserRole(sysUserRole);
    }
	
  
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysUserRoleById(Integer id) {
    	return sysUserRoleMapper.deleteSysUserRoleById(id);
    }
	
}