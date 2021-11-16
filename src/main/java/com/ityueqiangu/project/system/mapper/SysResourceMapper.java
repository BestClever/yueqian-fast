package com.ityueqiangu.project.system.mapper;

import java.util.List;

import com.ityueqiangu.core.web.domain.SysMenu;
import com.ityueqiangu.project.system.domain.SysResource;

/**
 * @author FlowerStone
 * @title: SysResourceMapper
 * @description:
 * @date 2021-11-14 10:38:35
 */
public interface SysResourceMapper {

    /**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
    List<SysResource> selectSysResourceList(SysResource sysResource);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    SysResource selectSysResourceById(Integer id);

    /**
     * 新增，插入所有字段
     *
     * @param sysResource 新增的记录
     * @return 返回影响行数
     */
    Integer insertSysResource(SysResource sysResource);


    /**
     * 修改
     *
     * @param sysResource 修改的记录
     * @return 返回影响行数
     */
    Integer updateSysResource(SysResource sysResource);


    /**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
    Integer deleteSysResourceById(Integer id);

    /**
     * 根据条件删除记录
     *
     * @param sysResource
     * @return 返回影响行数
     */
    Integer deleteSysResourceByCondition(SysResource sysResource);

    /**
     * 查询单个记录
     *
     * @return
     * @author FlowerStone
     * @date 2021年11月15日 0015 9:48:45
     */
    SysResource getOne(SysResource sysResource);

    /**
     * 根据用户名获取菜单
     *
     * @param userName
     * @return
     * @author FlowerStone
     * @date 2021年11月15日 0015 22:12:58
     */
    List<SysMenu> selectMenuByUsername(String userName);
}