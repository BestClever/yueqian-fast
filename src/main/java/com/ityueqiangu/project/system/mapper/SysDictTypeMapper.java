package com.ityueqiangu.project.system.mapper;

import java.util.List;

import com.ityueqiangu.project.system.domain.SysDictData;
import com.ityueqiangu.project.system.domain.SysDictType;

/**
 * @author FlowerStone
 * @title: SysDictTypeMapper
 * @description:
 * @date 2022-03-21 17:06:28
 */
public interface SysDictTypeMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<SysDictType> selectSysDictTypeList(SysDictType sysDictType);


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	SysDictType selectSysDictTypeById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param sysDictType 新增的记录
     * @return 返回影响行数
     */
	Integer insertSysDictType(SysDictType sysDictType);
	
	
	/**
     * 修改
     *
     * @param sysDictType 修改的记录
     * @return 返回影响行数
     */
	Integer updateSysDictType(SysDictType sysDictType);
	

	/**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
	Integer deleteSysDictTypeById(Integer id);
    
    /**
     * 根据条件删除记录
     *
     * @param sysDictType}
     * @return 返回影响行数
     */
	Integer deleteSysDictTypeByCondition(SysDictType sysDictType);
}