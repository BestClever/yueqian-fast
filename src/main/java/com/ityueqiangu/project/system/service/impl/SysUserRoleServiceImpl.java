package com.ityueqiangu.project.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ityueqiangu.common.constant.Constants;
import com.ityueqiangu.project.system.domain.SysRole;
import com.ityueqiangu.project.system.domain.SysUserRole;
import com.ityueqiangu.project.system.mapper.SysUserRoleMapper;
import com.ityueqiangu.project.system.service.ISysRoleService;
import com.ityueqiangu.project.system.service.ISysUserRoleService;
import com.ityueqiangu.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FlowerStone
 * @title: SysUserRoleServiceImpl
 * @description:
 * @date 2021-11-14 07:11:02
 */
@Service
public class SysUserRoleServiceImpl implements ISysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<SysUserRole> selectSysUserRoleList(SysUserRole sysUserRole) {
        return sysUserRoleMapper.selectSysUserRoleList(sysUserRole);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysUserRole selectSysUserRoleById(Integer id) {
        return sysUserRoleMapper.selectSysUserRoleById(id);
    }

    /**
     * 新增
     *
     * @param sysUserRole 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysUserRole(SysUserRole sysUserRole) {
        return sysUserRoleMapper.insertSysUserRole(sysUserRole);
    }

    /**
     * 批量插入
     *
     * @param sysUserRole
     * @return
     * @author FlowerStone
     * @date 2021年11月14日 0014 7:35:00
     */
    @Override
    public Integer batchInsert(List<SysUserRole> sysUserRole) {
        return sysUserRoleMapper.batchInsert(sysUserRole);
    }


    /**
     * 修改
     *
     * @param sysUserRole 修改的记录
     * @return 返回
     */
    public Integer updateSysUserRole(SysUserRole sysUserRole) {
        return sysUserRoleMapper.updateSysUserRole(sysUserRole);
    }


    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysUserRoleById(Integer id) {
        return sysUserRoleMapper.deleteSysUserRoleById(id);
    }

    /**
     * 根据条件删除记录
     *
     * @param sysUserRole
     * @return
     * @author FlowerStone
     * @date 2021年11月14日 0014 9:05:17
     */
    @Override
    public Integer deleteSysUserRoleByCondition(SysUserRole sysUserRole) {
        return sysUserRoleMapper.deleteSysUserRoleByCondition(sysUserRole);
    }

    /**
     * 获取用户角色
     *
     * @param id
     * @return
     * @author FlowerStone
     * @date 2021年11月14日 0014 8:42:20
     */
    @Override
    public List<SysRole> getUserRole(Integer id) {
        //获取所有有效的角色
        SysRole sysRole = new SysRole();
        sysRole.setIsEnable(Constants.SUCCESS);
        List<SysRole> allRoles = sysRoleService.selectSysRoleList(sysRole);
        //获取选中的角色
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(id);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectSysUserRoleList(sysUserRole);
        allRoles.stream().forEach(item -> {
            sysUserRoles.forEach(element -> {
                //如果相等 设置选中状态为选中
                if (ObjectUtil.equal(item.getId(), element.getRoleId())) {
                    item.setChecked(true);
                }
            });
        });
        return allRoles;
    }

}