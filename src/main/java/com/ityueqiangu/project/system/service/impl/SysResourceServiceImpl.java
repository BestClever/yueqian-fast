package com.ityueqiangu.project.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.core.exception.BusinessException;
import com.ityueqiangu.core.web.domain.SysMenu;
import com.ityueqiangu.project.system.domain.SysResource;
import com.ityueqiangu.project.system.mapper.SysResourceMapper;
import com.ityueqiangu.project.system.service.ISysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author FlowerStone
 * @title: SysResourceServiceImpl
 * @description:
 * @date 2021-11-14 10:38:35
 */
@Service
public class SysResourceServiceImpl implements ISysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
     public List<SysResource> selectSysResourceList(SysResource sysResource) {
         List<SysResource> sysResources = sysResourceMapper.selectSysResourceList(sysResource);
         return sysResources.stream().sorted(Comparator.comparing(SysResource::getSort)).collect(Collectors.toList());
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysResource selectSysResourceById(Integer id) {
    	return sysResourceMapper.selectSysResourceById(id);
    }
	
    /**
     * 新增
     *
     * @param sysResource 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysResource(SysResource sysResource) {
        //默认启用
        sysResource.setIsEnable(Constants.YES);
    	return sysResourceMapper.insertSysResource(sysResource);
    }
	
	
    /**
     * 修改
     *
     * @param sysResource 修改的记录
     * @return 返回
     */
    public Integer updateSysResource(SysResource sysResource) {
    	return sysResourceMapper.updateSysResource(sysResource);
    }
	
  
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysResourceById(Integer id) {
        //判断是否存在子节点
        SysResource paramResource = new SysResource();
        paramResource.setParentId(id);
        List<SysResource> sysResource = sysResourceMapper.selectSysResourceList(paramResource);
        if (CollectionUtil.isNotEmpty(sysResource)) {
            throw new BusinessException("存在子节点不能删除！");
        }
    	return sysResourceMapper.deleteSysResourceById(id);
    }

    /**
     * 修改启用状态
     * @author FlowerStone
     * @date 2021年11月15日 0015 14:50:07
     * @param sysResource
     * @return
     */
    @Override
    public Integer updateStatus(SysResource sysResource) {
        return sysResourceMapper.updateSysResource(sysResource);
    }

    /**
     * 查询菜单数据 admin查询出所有的菜单
     *
     * @param userName
     * @return
     * @date 2022年03月21日 0021 18:09:05
     */
    @Override
    public List<SysMenu> selectMenuByUsername(String userName) {
        List<SysMenu> menus = null;
        if (StrUtil.equals(userName,Constants.ADMIN)){
            menus = sysResourceMapper.selectMenu();
        }else {
            menus = sysResourceMapper.selectMenuByUsername(userName);
        }
        if (CollectionUtil.isEmpty(menus)) {
            throw new BusinessException("未查到菜单数据");
        }
        // 排序
        List<SysMenu> menusSorteds = menus.stream().sorted(Comparator.comparing(SysMenu::getSort)).collect(Collectors.toList());
        return menusSorteds;
    }

}