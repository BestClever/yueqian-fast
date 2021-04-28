package com.ityueqiangu.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.ityueqiangu.system.domain.SysRolePermission;

/**
 * @author Clever、xia
 * @title: SysRolePermissionMapper
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Mapper
public interface SysRolePermissionMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<SysRolePermission> selectSysRolePermissionList(SysRolePermission sysRolePermission);


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	SysRolePermission selectSysRolePermissionById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param sysRolePermission 新增的记录
     * @return 返回影响行数
     */
	Integer insertSysRolePermission(SysRolePermission sysRolePermission);
	
	
	/**
     * 修改
     *
     * @param sysRolePermission 修改的记录
     * @return 返回影响行数
     */
	Integer updateSysRolePermission(SysRolePermission sysRolePermission);
	

	/**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
	Integer deleteSysRolePermissionById(Integer id);
	
}