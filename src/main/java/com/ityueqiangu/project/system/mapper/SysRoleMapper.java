package com.ityueqiangu.project.system.mapper;

import com.ityueqiangu.project.system.domain.SysRole;

import java.util.List;

/**
 * @author FlowerStone
 * @title: SysRoleMapper
 * @description:
 * @date 2021-11-13 14:05:24
 */
public interface SysRoleMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<SysRole> selectSysRoleList(SysRole sysRole);


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	SysRole selectSysRoleById(Integer id);

	/**
	 *  获取单个记录
	 * @author FlowerStone
	 * @date 2021年11月13日 0013 20:51:37
	 * @param sysRole
	 * @return
	 */
	SysRole getOne(SysRole sysRole);

	/**
     * 新增，插入所有字段
     *
     * @param sysRole 新增的记录
     * @return 返回影响行数
     */
	Integer insertSysRole(SysRole sysRole);
	
	
	/**
     * 修改
     *
     * @param sysRole 修改的记录
     * @return 返回影响行数
     */
	Integer updateSysRole(SysRole sysRole);
	

	/**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
	Integer deleteSysRoleById(Integer id);
	
}