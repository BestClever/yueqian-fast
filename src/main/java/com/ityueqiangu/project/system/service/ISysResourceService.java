package com.ityueqiangu.project.system.service;

import com.ityueqiangu.core.web.domain.SysMenu;
import com.ityueqiangu.project.system.domain.SysResource;

import java.util.List;

/**
 * @author FlowerStone
 * @title: SysResourceService
 * @description:
 * @date 2021-11-14 10:38:35
 */
public interface ISysResourceService {

    /**
     * 查询所有记录
     *
     * @return list集合
     */
    List<SysResource> selectSysResourceList(SysResource sysResource);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回SysResource
     */
    SysResource selectSysResourceById(Integer id);

    /**
     * 新增
     *
     * @param sysResource 新增的记录
     * @return 返回ResultInfo
     */
    Integer insertSysResource(SysResource sysResource);


    /**
     * 修改，修改所有字段
     *
     * @param sysResource 修改的记录
     * @return 返回ResultInfo
     */
    Integer updateSysResource(SysResource sysResource);

    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    Integer deleteSysResourceById(Integer id);

    /**
     * 修改启用状态
     *
     * @param sysResource
     * @return
     * @author FlowerStone
     * @date 2021年11月15日 0015 14:51:30
     */
    Integer updateStatus(SysResource sysResource);

    /**
     * 根据用户名查找菜单
     *
     * @param userName
     * @return
     * @author FlowerStone
     * @date 2021年11月15日 0015 22:10:06
     */
    List<SysMenu> selectMenuByUsername(String userName);
}