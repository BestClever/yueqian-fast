package com.ityueqiangu.project.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ityueqiangu.common.exception.BusinessException;
import com.ityueqiangu.core.web.ActiverUser;
import com.ityueqiangu.project.system.domain.SysUser;
import com.ityueqiangu.project.system.domain.SysUserRole;
import com.ityueqiangu.project.system.mapper.SysUserMapper;
import com.ityueqiangu.project.system.service.ISysUserRoleService;
import com.ityueqiangu.project.system.service.ISysUserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FlowerStone
 * @title: SysUserServiceImpl
 * @description:
 * @date 2021-11-10 21:46:24
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

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
    public SysUser selectSysUserById(Integer id) {
        return sysUserMapper.selectSysUserById(id);
    }

    /**
     * 新增
     *
     * @param sysUser 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysUser(SysUser sysUser) {
        SysUser param = new SysUser();
        param.setUserName(sysUser.getUserName());
        //判断登录名是否存在 如果存在不让新增
        SysUser result = sysUserMapper.getOne(param);
        if (ObjectUtil.isNotEmpty(result)) {
            throw new BusinessException("用户存在，请换一个登录名称");
        }
        //插入用户表
        sysUserMapper.insertSysUser(sysUser);
        //将用户与角色的关系插入到 用户角色表中
        List<SysUserRole> sysUserRoles = new ArrayList<SysUserRole>();
        for (String roleId : StrUtil.split(sysUser.getRoleIds(), ",")) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(Integer.valueOf(roleId));
            sysUserRole.setUserId(sysUser.getId());
            sysUserRoles.add(sysUserRole);
        }
        return sysUserRoleService.batchInsert(sysUserRoles);
    }


    /**
     * 修改
     *
     * @param sysUser 修改的记录
     * @return 返回
     */
    public Integer updateSysUser(SysUser sysUser) {
        //删除 用户角色的关系 重新建立
        //1.删除用户角色的关系
        SysUserRole sysUserRoleParam = new SysUserRole();
        sysUserRoleParam.setUserId(sysUser.getId());
        sysUserRoleService.deleteSysUserRoleByCondition(sysUserRoleParam);
        //2.重新建立用户角色的关系
        List<SysUserRole> sysUserRoles = new ArrayList<SysUserRole>();
        for (String roleId : StrUtil.split(sysUser.getRoleIds(), ",")) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(Integer.valueOf(roleId));
            sysUserRole.setUserId(sysUser.getId());
            sysUserRoles.add(sysUserRole);
        }
        sysUserRoleService.batchInsert(sysUserRoles);
        return sysUserMapper.updateSysUser(sysUser);
    }


    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysUserById(Integer id) {
        return sysUserMapper.deleteSysUserById(id);
    }

    /**
     * 查询单个系统用户对象
     *
     * @param sysUser
     * @return
     * @author FlowerStone
     * @date 2021年11月11日 0011 15:02:53
     */
    @Override
    public SysUser getOne(SysUser sysUser) {
        return sysUserMapper.getOne(sysUser);
    }

    /**
     * 修改用户状态
     *
     * @param sysUser
     * @return
     * @author FlowerStone
     * @date 2021年11月14日 0014 9:27:41
     */
    @Override
    public Integer updateSysUserStatus(SysUser sysUser) {
        return sysUserMapper.updateSysUser(sysUser);
    }

}