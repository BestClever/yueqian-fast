package com.ityueqiangu.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.ityueqiangu.system.domain.SysRole;

/**
 * @author Clever、xia
 * @title: SysRoleMapper
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Mapper
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

    SysRole getOne(SysRole sysRole);
}