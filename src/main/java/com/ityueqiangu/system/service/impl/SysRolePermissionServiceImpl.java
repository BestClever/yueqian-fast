package com.ityueqiangu.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ityueqiangu.core.enums.SysFlagStatusEnum;
import com.ityueqiangu.core.exception.BizException;
import com.ityueqiangu.system.domain.SysPermission;
import com.ityueqiangu.system.domain.SysRolePermission;
import com.ityueqiangu.system.mapper.SysRolePermissionMapper;
import com.ityueqiangu.system.service.ISysPermissionService;
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

    @Autowired
    private ISysPermissionService sysPermissionService;

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

    @Override
    public void saveRelationship(SysRolePermission sysRolePermission) {
        if (ObjectUtil.isNull(sysRolePermission.getIds())) {
            throw new BizException("传入的权限id不能为空");
        }
        //删除 该角色的所有权限关系
        sysRolePermissionMapper.deleteSysRolePermissionByRoleId(sysRolePermission.getRoleId());
        for (String str : sysRolePermission.getIds()) {
            sysRolePermission.setPermissionId(Integer.valueOf(str));
            sysRolePermissionMapper.insertSysRolePermission(sysRolePermission);
        }
    }

    @Override
    public List<SysPermission> getRolePermission(SysRolePermission sysRolePermission) {
        List<SysPermission> sysPermissions = sysPermissionService.selectSysPermissionList(null);
        SysRolePermission param = new SysRolePermission();
        param.setRoleId(sysRolePermission.getRoleId());
        List<SysRolePermission> sysRolePermissions = sysRolePermissionMapper.selectSysRolePermissionList(param);
        sysPermissions.stream().forEach(sysPermission -> {
            sysRolePermissions.stream().forEach(rolePermission->{
                if (ObjectUtil.equal(sysPermission.getId(),rolePermission.getPermissionId())) {
                    sysPermission.setCheckArr(SysFlagStatusEnum.ONE.getKey());
                }
            });
        });
        return sysPermissions;
    }

    /**
     * 根据 角色id 删除 角色权限关联表
     * @param roleId
     */
    @Override
    public void deleteSysRolePermissionByRoleId(Integer roleId) {

    }

}