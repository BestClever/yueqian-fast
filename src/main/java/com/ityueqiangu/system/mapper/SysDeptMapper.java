package com.ityueqiangu.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.ityueqiangu.system.domain.SysDept;

/**
 * @author Clever、xia
 * @title: SysDeptMapper
 * @description:
 * @date 2021-04-25 17:33:01
 */
@Mapper
public interface SysDeptMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<SysDept> selectSysDeptList(SysDept sysDept);


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	SysDept selectSysDeptById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param sysDept 新增的记录
     * @return 返回影响行数
     */
	Integer insertSysDept(SysDept sysDept);
	
	
	/**
     * 修改
     *
     * @param sysDept 修改的记录
     * @return 返回影响行数
     */
	Integer updateSysDept(SysDept sysDept);
	

	/**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
	Integer deleteSysDeptById(Integer id);

	SysDept getOne(SysDept sysDept);
	
}