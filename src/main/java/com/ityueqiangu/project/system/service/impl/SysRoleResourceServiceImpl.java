package com.ityueqiangu.project.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ityueqiangu.common.exception.BusinessException;
import com.ityueqiangu.project.system.domain.SysRoleResource;
import com.ityueqiangu.project.system.mapper.SysRoleResourceMapper;
import com.ityueqiangu.project.system.service.ISysRoleResourceService;
import com.ityueqiangu.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FlowerStone
 * @title: SysRoleResourceServiceImpl
 * @description:
 * @date 2021-11-14 07:11:02
 */
@Service
public class SysRoleResourceServiceImpl implements ISysRoleResourceService {

    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<SysRoleResource> selectSysRoleResourceList(SysRoleResource sysRoleResource) {
        return sysRoleResourceMapper.selectSysRoleResourceList(sysRoleResource);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysRoleResource selectSysRoleResourceById(Integer id) {
        return sysRoleResourceMapper.selectSysRoleResourceById(id);
    }

    /**
     * 新增
     *
     * @param sysRoleResource 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysRoleResource(SysRoleResource sysRoleResource) {
        return sysRoleResourceMapper.insertSysRoleResource(sysRoleResource);
    }


    /**
     * 修改
     *
     * @param sysRoleResource 修改的记录
     * @return 返回
     */
    public Integer updateSysRoleResource(SysRoleResource sysRoleResource) {
        return sysRoleResourceMapper.updateSysRoleResource(sysRoleResource);
    }


    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysRoleResourceById(Integer id) {
        return sysRoleResourceMapper.deleteSysRoleResourceById(id);
    }

    /**
     * 保存角色资源信息
     *
     * @param sysRoleResource
     * @return
     * @author FlowerStone
     * @date 2021年11月15日 0015 20:52:42
     */
    @Override
    public void saveRoleResource(SysRoleResource sysRoleResource) {
        //1.根据roleid 删除角色资源关联信息
        sysRoleResourceMapper.deleteSysRoleResourceByCondition(sysRoleResource);
        //2.插入角色资源关联信息
        if (StrUtil.isBlank(sysRoleResource.getResourceIds())) {
            throw new BusinessException("角色ids不能为空");
        }
        List<String> resourceIds = StrUtil.split(sysRoleResource.getResourceIds(), ",");
        ArrayList<SysRoleResource> sysRoleResources = new ArrayList<>();
        for (String resourceId : resourceIds) {
            SysRoleResource sysRoleResourceParam = new SysRoleResource();
            sysRoleResourceParam.setResourceId(Integer.parseInt(resourceId));
            sysRoleResourceParam.setRoleId(sysRoleResource.getRoleId());
            sysRoleResources.add(sysRoleResourceParam);
        }
        sysRoleResourceMapper.batchInsertRoleResource(sysRoleResources);
    }

}