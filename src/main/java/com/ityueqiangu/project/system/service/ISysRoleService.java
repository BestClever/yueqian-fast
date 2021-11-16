package com.ityueqiangu.project.system.service;

import java.util.List;

import com.ityueqiangu.core.web.domain.Dtree;
import com.ityueqiangu.project.system.domain.SysRole;

/**
 * @author FlowerStone
 * @title: SysRoleService
 * @description:
 * @date 2021-11-13 14:05:24
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

    /**
     * 获取单个对象
     * @author FlowerStone
     * @date 2021年11月13日 0013 20:54:04
     * @param sysRole
     * @return
     */
    SysRole getOne(SysRole sysRole);

    /**
     * 获取角色分配的资源
     * @author FlowerStone
     * @date 2021年11月15日 0015 15:03:00
     * @param roleId
     * @return
     */
    List<Dtree> getRoleResource(Integer roleId);
}