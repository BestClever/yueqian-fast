package com.ityueqiangu.project.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ityueqiangu.common.exception.BusinessException;
import com.ityueqiangu.core.web.domain.Dtree;
import com.ityueqiangu.project.system.domain.SysResource;
import com.ityueqiangu.project.system.domain.SysRole;
import com.ityueqiangu.project.system.domain.SysRoleResource;
import com.ityueqiangu.project.system.mapper.SysRoleMapper;
import com.ityueqiangu.project.system.service.ISysResourceService;
import com.ityueqiangu.project.system.service.ISysRoleResourceService;
import com.ityueqiangu.project.system.service.ISysRoleService;
import com.ityueqiangu.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private ISysRoleResourceService sysRoleResourceService;

    @Autowired
    private ISysResourceService sysResourceService;

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

    /**
     * 查询角色分配的资源
     *
     * @param roleId
     * @return
     * @author FlowerStone
     * @date 2021年11月15日 0015 15:03:36
     */
    @Override
    public List<Dtree> getRoleResource(Integer roleId) {
        ArrayList<Dtree> dtrees = new ArrayList<>();
        SysRoleResource sysRoleResourceParam = new SysRoleResource();
        sysRoleResourceParam.setRoleId(roleId);
        //查询角色资源管理表
        List<SysRoleResource> sysRoleResources = sysRoleResourceService.selectSysRoleResourceList(sysRoleResourceParam);
        //查询所有的资源
        List<SysResource> sysResources = sysResourceService.selectSysResourceList(null);
        sysResources.stream().forEach(element -> {
            Dtree dtree = new Dtree();
            dtree.setId(element.getId());
            dtree.setParentId(element.getParentId());
            dtree.setTitle(element.getResourceName());
            sysRoleResources.stream().forEach(item -> {
                if (ObjectUtil.equal(item.getResourceId(), element.getId())) {
                    dtree.setCheckArr("1");//"1"表示选中
                }
            });
            dtrees.add(dtree);
        });
        return dtrees;
    }

}