package com.ityueqiangu.project.system.mapper;

import java.util.List;

import com.ityueqiangu.project.system.domain.SysUserRole;

/**
 * @author FlowerStone
 * @title: SysUserRoleMapper
 * @description:
 * @date 2021-11-14 07:11:02
 */
public interface SysUserRoleMapper {

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    List<SysUserRole> selectSysUserRoleList(SysUserRole sysUserRole);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    SysUserRole selectSysUserRoleById(Integer id);

    /**
     * 新增，插入所有字段
     *
     * @param sysUserRole 新增的记录
     * @return 返回影响行数
     */
    Integer insertSysUserRole(SysUserRole sysUserRole);

    /**
     * 批量插入
     *
     * @param sysUserRole
     * @return
     * @author FlowerStone
     * @date 2021年11月14日 0014 7:33:38
     */
    Integer batchInsert(List<SysUserRole> sysUserRole);


    /**
     * 修改
     *
     * @param sysUserRole 修改的记录
     * @return 返回影响行数
     */
    Integer updateSysUserRole(SysUserRole sysUserRole);


    /**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
    Integer deleteSysUserRoleById(Integer id);

    Integer deleteSysUserRoleByCondition(SysUserRole sysUserRole);
}