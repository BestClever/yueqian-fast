package com.ityueqiangu.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.ityueqiangu.system.domain.SysUserRole;

/**
 * @author Clever、xia
 * @title: SysUserRoleMapper
 * @description:
 * @date 2021-04-27 16:58:27
 */
@Mapper
public interface SysUserRoleMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<SysUserRole> selectSysUserRoleList(SysUserRole sysUserRole);


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	SysUserRole selectSysUserRoleById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param sysUserRole 新增的记录
     * @return 返回影响行数
     */
	Integer insertSysUserRole(SysUserRole sysUserRole);
	
	
	/**
     * 修改
     *
     * @param sysUserRole 修改的记录
     * @return 返回影响行数
     */
	Integer updateSysUserRole(SysUserRole sysUserRole);
	

	/**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
	Integer deleteSysUserRoleById(Integer id);

	/**
	 * 根据 userid 删除用户角色信息
	 * @param userId
	 * @return
	 */
    Integer deleteSysUserRoleByUserId(Integer userId);
}