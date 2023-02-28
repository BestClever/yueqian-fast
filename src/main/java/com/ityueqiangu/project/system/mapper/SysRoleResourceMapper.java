package com.ityueqiangu.project.system.mapper;

import com.ityueqiangu.project.system.domain.SysRoleResource;

import java.util.List;

/**
 * @author FlowerStone
 * @title: SysRoleResourceMapper
 * @description:
 * @date 2021-11-14 07:11:02
 */
public interface SysRoleResourceMapper {

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    List<SysRoleResource> selectSysRoleResourceList(SysRoleResource sysRoleResource);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    SysRoleResource selectSysRoleResourceById(Integer id);

    /**
     * 新增，插入所有字段
     *
     * @param sysRoleResource 新增的记录
     * @return 返回影响行数
     */
    Integer insertSysRoleResource(SysRoleResource sysRoleResource);


    /**
     * 修改
     *
     * @param sysRoleResource 修改的记录
     * @return 返回影响行数
     */
    Integer updateSysRoleResource(SysRoleResource sysRoleResource);


    /**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
    Integer deleteSysRoleResourceById(Integer id);


    /**
     * 批量插入角色资源信息
     * @author FlowerStone
     * @date 2021年11月15日 0015 21:12:41
     * @param sysRoleResource
     * @return
     */
    Integer batchInsertRoleResource(List<SysRoleResource> sysRoleResource);

    /**
     * 根据条件删除角色资源信息
     *
     * @param sysRoleResource
     * @return
     * @author FlowerStone
     * @date 2021年11月15日 0015 21:00:36
     */
	Integer deleteSysRoleResourceByCondition(SysRoleResource sysRoleResource);
}