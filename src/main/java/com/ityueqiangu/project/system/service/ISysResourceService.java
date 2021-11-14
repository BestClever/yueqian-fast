package com.ityueqiangu.project.system.service;

import java.util.List;
import com.ityueqiangu.project.system.domain.SysResource;

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
	
}