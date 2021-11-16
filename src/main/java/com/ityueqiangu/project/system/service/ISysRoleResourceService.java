package com.ityueqiangu.project.system.service;

import java.util.List;

import com.ityueqiangu.project.system.domain.SysRoleResource;

/**
 * @author FlowerStone
 * @title: SysRoleResourceService
 * @description:
 * @date 2021-11-14 07:11:02
 */
public interface ISysRoleResourceService {

    /**
     * 查询所有记录
     *
     * @return list集合
     */
    List<SysRoleResource> selectSysRoleResourceList(SysRoleResource sysRoleResource);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回SysRoleResource
     */
    SysRoleResource selectSysRoleResourceById(Integer id);

    /**
     * 新增
     *
     * @param sysRoleResource 新增的记录
     * @return 返回ResultInfo
     */
    Integer insertSysRoleResource(SysRoleResource sysRoleResource);


    /**
     * 修改，修改所有字段
     *
     * @param sysRoleResource 修改的记录
     * @return 返回ResultInfo
     */
    Integer updateSysRoleResource(SysRoleResource sysRoleResource);

    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    Integer deleteSysRoleResourceById(Integer id);

    /**
     * 保存角色资源信息
     *
     * @param sysRoleResource
     * @return
     * @author FlowerStone
     * @date 2021年11月15日 0015 20:52:19
     */
    void saveRoleResource(SysRoleResource sysRoleResource);
}