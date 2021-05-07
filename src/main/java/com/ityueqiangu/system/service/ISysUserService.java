package com.ityueqiangu.system.service;

import com.ityueqiangu.system.domain.SysUser;

import java.util.List;

/**
 * @author Clever、xia
 * @title: SysUserService
 * @description:
 * @date 2021-04-03 19:52:56
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
    SysUser selectSysUserById(Integer id);
	
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
    Integer deleteSysUserById(Integer id);

    /**
     * 根据登录名查询用户信息
     * @param sysUser
     * @return
     */
    SysUser selectSysUserByLoginName(SysUser sysUser);

    /**
     * 保存用户信息
     * @param sysUser
     * @return
     */
    Integer saveUserInfo(SysUser sysUser);
}