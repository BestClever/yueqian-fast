package com.ityueqiangu.project.system.service.impl;

import com.ityueqiangu.core.web.ActiverUser;
import com.ityueqiangu.project.system.domain.SysUser;
import com.ityueqiangu.project.system.mapper.SysUserMapper;
import com.ityueqiangu.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author FlowerStone
 * @title: SysUserServiceImpl
 * @description:
 * @date 2021-11-10 21:46:24
 */
@Service
public class SysUserServiceImpl implements ISysUserService{

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<SysUser> selectSysUserList(SysUser sysUser) {
        return sysUserMapper.selectSysUserList(sysUser);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysUser selectSysUserById(String id) {
        return sysUserMapper.selectSysUserById(id);
    }

    /**
     * 新增
     *
     * @param sysUser 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysUser(SysUser sysUser) {
        return sysUserMapper.insertSysUser(sysUser);
    }


    /**
     * 修改
     *
     * @param sysUser 修改的记录
     * @return 返回
     */
    public Integer updateSysUser(SysUser sysUser) {
        return sysUserMapper.updateSysUser(sysUser);
    }


    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysUserById(String id) {
        return sysUserMapper.deleteSysUserById(id);
    }

    /**
     * 查询单个系统用户对象
     * @author FlowerStone
     * @date 2021年11月11日 0011 15:02:53
     * @param sysUser
     * @return
     */
    @Override
    public SysUser getOne(SysUser sysUser) {
        return sysUserMapper.getOne(sysUser);
    }

}