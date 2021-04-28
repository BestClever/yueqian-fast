package com.ityueqiangu.system.service;

import java.util.List;
import com.ityueqiangu.system.domain.SysUserRole;

/**
 * @author Clever、xia
 * @title: SysUserRoleService
 * @description:
 * @date 2021-04-27 16:58:27
 */
public interface ISysUserRoleService {

    /**
     * 查询所有记录
     *
     * @return list集合
     */
    List<SysUserRole> selectSysUserRoleList(SysUserRole sysUserRole);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回SysUserRole
     */
    SysUserRole selectSysUserRoleById(Integer id);
	
    /**
     * 新增
     *
     * @param sysUserRole 新增的记录
     * @return 返回ResultInfo
     */
    Integer insertSysUserRole(SysUserRole sysUserRole);
	
	
    /**
     * 修改，修改所有字段
     *
     * @param sysUserRole 修改的记录
     * @return 返回ResultInfo
     */
    Integer updateSysUserRole(SysUserRole sysUserRole);
	
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    Integer deleteSysUserRoleById(Integer id);
	
}