package com.ityueqiangu.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.ityueqiangu.system.domain.SysPermission;

/**
 * @author Clever、xia
 * @title: SysPermissionMapper
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Mapper
public interface SysPermissionMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<SysPermission> selectSysPermissionList(SysPermission sysPermission);


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	SysPermission selectSysPermissionById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param sysPermission 新增的记录
     * @return 返回影响行数
     */
	Integer insertSysPermission(SysPermission sysPermission);
	
	
	/**
     * 修改
     *
     * @param sysPermission 修改的记录
     * @return 返回影响行数
     */
	Integer updateSysPermission(SysPermission sysPermission);
	

	/**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
	Integer deleteSysPermissionById(Integer id);

	/**
	 * 根据用户id查询菜单
	 * @param userId
	 * @return
	 */
	List<SysPermission> selectPermissionByUserId(Integer userId);
	
}