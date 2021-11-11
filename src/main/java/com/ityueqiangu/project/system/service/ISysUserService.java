package com.ityueqiangu.project.system.service;

import java.util.List;

import com.ityueqiangu.core.web.ActiverUser;
import com.ityueqiangu.project.system.domain.SysUser;

/**
 * @author Clever、xia
 * @title: SysUserService
 * @description:
 * @date 2021-11-10 21:46:24
 */
public interface ISysUserService {

    /**
     * 查询所有记录
     *
     * @return list集合
     */
    List<SysUser> selectSysUserList(SysUser sysUser);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回SysUser
     */
    SysUser selectSysUserById(String id);

    /**
     * 新增
     *
     * @param sysUser 新增的记录
     * @return 返回ResultInfo
     */
    Integer insertSysUser(SysUser sysUser);


    /**
     * 修改，修改所有字段
     *
     * @param sysUser 修改的记录
     * @return 返回ResultInfo
     */
    Integer updateSysUser(SysUser sysUser);

    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    Integer deleteSysUserById(String id);

    /**
     * 查询单个系统用户对象
     * @author FlowerStone
     * @date 2021年11月11日 0011 15:02:25
     * @param sysUser
     * @return
     */
    SysUser getOne(SysUser sysUser);
}