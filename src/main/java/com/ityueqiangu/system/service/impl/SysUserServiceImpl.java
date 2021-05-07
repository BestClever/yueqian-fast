package com.ityueqiangu.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ityueqiangu.core.exception.BizException;
import com.ityueqiangu.core.util.StringUtils;
import com.ityueqiangu.system.domain.SysDept;
import com.ityueqiangu.system.domain.SysUser;
import com.ityueqiangu.system.domain.SysUserRole;
import com.ityueqiangu.system.mapper.SysUserMapper;
import com.ityueqiangu.system.service.ISysDeptService;
import com.ityueqiangu.system.service.ISysUserRoleService;
import com.ityueqiangu.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Clever、xia
 * @title: SysUserServiceImpl
 * @description:
 * @date 2021-04-03 19:52:56
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
     public List<SysUser> selectSysUserList(SysUser sysUser) {
         List<SysUser> sysUsers = sysUserMapper.selectSysUserList(sysUser);
         List<SysDept> depts = sysDeptService.selectSysDeptList(null);
         Map<Integer, SysDept> deptMap = depts.stream().collect(Collectors.toMap(SysDept::getId, sysDept -> sysDept));
         sysUsers.stream().forEach(user->{
             SysDept sysDept = deptMap.get(user.getDeptId());
             if (ObjectUtil.isNotNull(sysDept)) {
                 user.setDeptName(sysDept.getDeptName());
             }
         });
         return sysUsers;
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
    	return sysUserMapper.insertSysUser(sysUser);
    }
	
	
    /**
     * 修改
     *
     * @param sysUser 修改的记录
     * @return 返回
     */
    public Integer updateSysUser(SysUser sysUser) {
        //删除 原来的 角色和用户的关系
    	return sysUserMapper.updateSysUser(sysUser);
    }
	
  
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysUserById(Integer id) {
        //删除 角色用户管理信息
        sysUserRoleService.deleteSysUserRoleByUserId(id);
    	return sysUserMapper.deleteSysUserById(id);
    }

    @Override
    public SysUser selectSysUserByLoginName(SysUser sysUser) {
        return sysUserMapper.getOne(sysUser);
    }


    @Override
    public Integer saveUserInfo(SysUser sysUser) {
        //保存用户的信息
        Integer result = this.insertSysUser(sysUser);
        //保存用户与角色的关联信息
        String roleIds = sysUser.getRoleIds();
        if (StringUtils.isBlank(roleIds)) {
            throw new BizException("未选择角色");
        }
        String[] strs = roleIds.split(",");
        for (String roleId : strs) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(Integer.valueOf(roleId));
            sysUserRole.setUserId(sysUser.getId());
            sysUserRoleService.insertSysUserRole(sysUserRole);
        }
        return result;
    }

}