package com.ityueqiangu.project.system.mapper;

import com.ityueqiangu.project.system.domain.SysUser;

import java.util.List;

/**
 * @author FlowerStone
 * @title: SysUserMapper
 * @description:
 * @date 2021-11-10 21:46:24
 */
public interface SysUserMapper {

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    List<SysUser> selectSysUserList(SysUser sysUser);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    SysUser selectSysUserById(Integer id);

    /**
     * 新增，插入所有字段
     *
     * @param sysUser 新增的记录
     * @return 返回影响行数
     */
    Integer insertSysUser(SysUser sysUser);


    /**
     * 修改
     *
     * @param sysUser 修改的记录
     * @return 返回影响行数
     */
    Integer updateSysUser(SysUser sysUser);


    /**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
    Integer deleteSysUserById(Integer id);

    /**
     * 查询单个用户对象
     * @author FlowerStone
     * @date 2021年11月11日 0011 15:04:01
     * @param sysUser
     * @return
     */
    SysUser getOne(SysUser sysUser);
}
