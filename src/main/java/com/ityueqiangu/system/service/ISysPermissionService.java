package com.ityueqiangu.system.service;

import java.util.List;

import com.ityueqiangu.core.web.vo.Menu;
import com.ityueqiangu.system.domain.SysPermission;

/**
 * @author Clever、xia
 * @title: SysPermissionService
 * @description:
 * @date 2021-04-27 16:58:27
 */
public interface ISysPermissionService {

    /**
     * 查询所有记录
     *
     * @return list集合
     */
    List<SysPermission> selectSysPermissionList(SysPermission sysPermission);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回SysPermission
     */
    SysPermission selectSysPermissionById(Integer id);
	
    /**
     * 新增
     *
     * @param sysPermission 新增的记录
     * @return 返回ResultInfo
     */
    Integer insertSysPermission(SysPermission sysPermission);
	
	
    /**
     * 修改，修改所有字段
     *
     * @param sysPermission 修改的记录
     * @return 返回ResultInfo
     */
    Integer updateSysPermission(SysPermission sysPermission);
	
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    Integer deleteSysPermissionById(Integer id);

    /**
     * 根据 用户id 生成菜单树
     * @param userId
     * @return
     */
    List<Menu> generateMenu(Integer userId);
	
}