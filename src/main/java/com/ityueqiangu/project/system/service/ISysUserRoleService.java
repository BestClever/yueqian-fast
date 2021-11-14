package com.ityueqiangu.project.system.service;

import java.util.List;

import com.ityueqiangu.project.system.domain.SysRole;
import com.ityueqiangu.project.system.domain.SysUserRole;

/**
 * @author FlowerStone
 * @title: SysUserRoleService
 * @description:
 * @date 2021-11-14 07:11:02
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
     * 批量插入
     *
     * @param sysUserRole
     * @return
     * @author FlowerStone
     * @date 2021年11月14日 0014 7:34:35
     */
    Integer batchInsert(List<SysUserRole> sysUserRole);


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

    /**
     * 根据条件删除记录
     *
     * @param sysUserRole
     * @return
     * @author FlowerStone
     * @date 2021年11月14日 0014 9:04:50
     */
    Integer deleteSysUserRoleByCondition(SysUserRole sysUserRole);

    /**
     * 获取用户角色
     *
     * @param id
     * @return
     * @author FlowerStone
     * @date 2021年11月14日 0014 8:41:13
     */
    List<SysRole> getUserRole(Integer id);
}