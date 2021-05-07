package com.ityueqiangu.system.service;

import java.util.List;
import com.ityueqiangu.system.domain.SysRole;

/**
 * @author Clever、xia
 * @title: SysRoleService
 * @description:
 * @date 2021-04-27 16:58:27
 */
public interface ISysRoleService {

    /**
     * 查询所有记录
     *
     * @return list集合
     */
    List<SysRole> selectSysRoleList(SysRole sysRole);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回SysRole
     */
    SysRole selectSysRoleById(Integer id);
	
    /**
     * 新增
     *
     * @param sysRole 新增的记录
     * @return 返回ResultInfo
     */
    Integer insertSysRole(SysRole sysRole);
	
	
    /**
     * 修改，修改所有字段
     *
     * @param sysRole 修改的记录
     * @return 返回ResultInfo
     */
    Integer updateSysRole(SysRole sysRole);
	
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    Integer deleteSysRoleById(Integer id);

    SysRole existRole(SysRole sysRole);

    List<SysRole> listRole(Integer userId);
}