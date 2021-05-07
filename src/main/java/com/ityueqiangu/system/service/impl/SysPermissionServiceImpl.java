package com.ityueqiangu.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.core.web.vo.Menu;
import com.ityueqiangu.system.domain.SysDept;
import com.ityueqiangu.system.domain.SysPermission;
import com.ityueqiangu.system.mapper.SysPermissionMapper;
import com.ityueqiangu.system.service.ISysPermissionService;
import com.ityueqiangu.core.util.StringUtils;
import com.ityueqiangu.core.enums.CommonEnum;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Clever、xia
 * @title: SysPermissionServiceImpl
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Service
public class SysPermissionServiceImpl implements ISysPermissionService{

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
     public List<SysPermission> selectSysPermissionList(SysPermission sysPermission) {
         List<SysPermission> sysPermissions = sysPermissionMapper.selectSysPermissionList(sysPermission);
         Map<Integer, SysPermission> sysPermissionMap = sysPermissions.stream().collect(Collectors.toMap(SysPermission::getId, permission -> permission));
         sysPermissions.stream().forEach(permission -> {
             SysPermission permissionResult = sysPermissionMap.get(permission.getParentId());
             if (ObjectUtil.isNotNull(permissionResult)) {
                 permission.setParentName(permissionResult.getPermissionName());
             }else {
                 permission.setParentName("无");
             }
         });
         return sysPermissions;
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysPermission selectSysPermissionById(Integer id) {
    	return sysPermissionMapper.selectSysPermissionById(id);
    }
	
    /**
     * 新增
     *
     * @param sysPermission 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysPermission(SysPermission sysPermission) {
    	return sysPermissionMapper.insertSysPermission(sysPermission);
    }
	
	
    /**
     * 修改
     *
     * @param sysPermission 修改的记录
     * @return 返回
     */
    public Integer updateSysPermission(SysPermission sysPermission) {
    	return sysPermissionMapper.updateSysPermission(sysPermission);
    }
	
  
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysPermissionById(Integer id) {
    	return sysPermissionMapper.deleteSysPermissionById(id);
    }

    /**
     * 生成菜单树
     * @param userId
     * @return
     */
    public List<Menu> generateMenu(Integer userId){
        ArrayList<Menu> menuList = new ArrayList<>();
        List<SysPermission> sysPermissions = sysPermissionMapper.selectPermissionByUserId(userId);
        //根据排序字段进行排序
        sysPermissions = sysPermissions.stream().sorted(Comparator.comparing(SysPermission::getSortNum)).collect(Collectors.toList());
        ArrayList<Menu> menus = Menu.adapterMenu(sysPermissions);
        Map<Integer, Menu> menuMap = menus.stream().collect(Collectors.toMap(Menu::getId, menu -> menu));
        //生成 树
        for (Menu menu : menus) {
            if (Constants.ZERO.equals(menu.getParentId())) {
                menuList.add(menu);
            }else{
                Menu menuParent = menuMap.get(menu.getParentId());
                menuParent.getChildren().add(menu);
            }
        }
        return menuList;
    }
	
}