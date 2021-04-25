package com.ityueqiangu.system.mapper;

import com.ityueqiangu.system.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Clever、xia
 * @title: SysUserMapper
 * @description:
 * @date 2021-04-03 19:52:56
 */
@Mapper
public interface SysUserMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<SysUser> selectSysUserList(SysUser sysUser);


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	SysUser selectSysUserById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param sysUser 新增的记录
     * @return 返回影响行数
     */
	Integer insertSysUser(SysUser sysUser);
	
	
	/**
     * 修改
     *
     * @param sysUser 修改的记录
     * @return 返回影响行数
     */
	Integer updateSysUser(SysUser sysUser);
	

	/**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
	Integer deleteSysUserById(Integer id);

	/**
	 * 查询单个
	 * @param sysUser
	 * @return
	 */
    SysUser getOne(SysUser sysUser);

    List<SysUser> notEvaluateList(SysUser sysUser);
}