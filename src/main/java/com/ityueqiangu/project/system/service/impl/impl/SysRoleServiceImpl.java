package com.ityueqiangu.project.system.service.impl.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ityueqiangu.common.exception.BusinessException;
import com.ityueqiangu.project.system.domain.SysRole;
import com.ityueqiangu.project.system.mapper.SysRoleMapper;
import com.ityueqiangu.project.system.service.ISysRoleService;
import com.ityueqiangu.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FlowerStone
 * @title: SysRoleServiceImpl
 * @description:
 * @date 2021-11-13 14:05:24
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<SysRole> selectSysRoleList(SysRole sysRole) {
        return sysRoleMapper.selectSysRoleList(sysRole);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysRole selectSysRoleById(Integer id) {
        return sysRoleMapper.selectSysRoleById(id);
    }

    /**
     * 新增
     *
     * @param sysRole 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysRole(SysRole sysRole) {
        //判断编码是否存在 如果存在 抛出存在异常
        SysRole param = new SysRole();
        param.setRoleCode(sysRole.getRoleCode());
        SysRole result = sysRoleMapper.getOne(param);
        if (ObjectUtil.isNotEmpty(result)) {
            throw new BusinessException("角色编码已经存在，请换一个编码！");
        }
        return sysRoleMapper.insertSysRole(sysRole);
    }


    /**
     * 修改
     *
     * @param sysRole 修改的记录
     * @return 返回
     */
    public Integer updateSysRole(SysRole sysRole) {
        return sysRoleMapper.updateSysRole(sysRole);
    }


    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysRoleById(Integer id) {
        return sysRoleMapper.deleteSysRoleById(id);
    }

    /**
     * 获取单个对象
     *
     * @param sysRole
     * @return
     * @author FlowerStone
     * @date 2021年11月13日 0013 20:53:31
     */
    public SysRole getOne(SysRole sysRole) {
        return sysRoleMapper.getOne(sysRole);
    }

}