package com.ityueqiangu.system.service;

import java.util.List;
import com.ityueqiangu.system.domain.SysRolePermission;

/**
 * @author Clever、xia
 * @title: SysRolePermissionService
 * @description:
 * @date 2021-04-27 16:58:27
 */
public interface ISysRolePermissionService {

    /**
     * 查询所有记录
     *
     * @return list集合
     */
    List<SysRolePermission> selectSysRolePermissionList(SysRolePermission sysRolePermission);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回SysRolePermission
     */
    SysRolePermission selectSysRolePermissionById(Integer id);
	
    /**
     * 新增
     *
     * @param sysRolePermission 新增的记录
     * @return 返回ResultInfo
     */
    Integer insertSysRolePermission(SysRolePermission sysRolePermission);
	
	
    /**
     * 修改，修改所有字段
     *
     * @param sysRolePermission 修改的记录
     * @return 返回ResultInfo
     */
    Integer updateSysRolePermission(SysRolePermission sysRolePermission);
	
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    Integer deleteSysRolePermissionById(Integer id);

    void saveRelationship(SysRolePermission sysRolePermission);
}