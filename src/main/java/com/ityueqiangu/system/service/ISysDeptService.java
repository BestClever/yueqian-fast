package com.ityueqiangu.system.service;

import java.util.List;
import com.ityueqiangu.system.domain.SysDept;

/**
 * @author Clever、xia
 * @title: SysDeptService
 * @description:
 * @date 2021-04-25 17:33:01
 */
public interface ISysDeptService {

    /**
     * 查询所有记录
     *
     * @return list集合
     */
    List<SysDept> selectSysDeptList(SysDept sysDept);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回SysDept
     */
    SysDept selectSysDeptById(Integer id);
	
    /**
     * 新增
     *
     * @param sysDept 新增的记录
     * @return 返回ResultInfo
     */
    Integer insertSysDept(SysDept sysDept);
	
	
    /**
     * 修改，修改所有字段
     *
     * @param sysDept 修改的记录
     * @return 返回ResultInfo
     */
    Integer updateSysDept(SysDept sysDept);
	
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    Integer deleteSysDeptById(Integer id);
	
}