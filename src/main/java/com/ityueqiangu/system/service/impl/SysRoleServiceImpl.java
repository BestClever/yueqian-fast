package com.ityueqiangu.system.service.impl;

import com.ityueqiangu.core.exception.BizException;
import com.ityueqiangu.system.domain.SysRole;
import com.ityueqiangu.system.mapper.SysRoleMapper;
import com.ityueqiangu.system.service.ISysRolePermissionService;
import com.ityueqiangu.system.service.ISysRoleService;
import com.ityueqiangu.core.util.StringUtils;
import com.ityueqiangu.core.enums.CommonEnum;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Clever、xia
 * @title: SysRoleServiceImpl
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService{

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private ISysRolePermissionService sysRolePermissionService;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
     public List<SysRole> selectSysRoleList(SysRole sysRole) {
       return sysRoleMapper.selectSysRoleList(sysRole);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysRole selectSysRoleById(Integer id) {
    	return sysRoleMapper.selectSysRoleById(id);
    }
	
    /**
     * 新增
     *
     * @param sysRole 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysRole(SysRole sysRole) {
    	return sysRoleMapper.insertSysRole(sysRole);
    }
	
	
    /**
     * 修改
     *
     * @param sysRole 修改的记录
     * @return 返回
     */
    public Integer updateSysRole(SysRole sysRole) {
    	return sysRoleMapper.updateSysRole(sysRole);
    }
	
  
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysRoleById(Integer id) {
    	return sysRoleMapper.deleteSysRoleById(id);
    }

    @Override
    public SysRole existRole(SysRole sysRole) {
        return sysRoleMapper.getOne(sysRole);
    }

}