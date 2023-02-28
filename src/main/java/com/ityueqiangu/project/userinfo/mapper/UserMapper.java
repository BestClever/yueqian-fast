package com.ityueqiangu.project.userinfo.mapper;


import com.ityueqiangu.project.userinfo.domain.User;

import java.util.List;

/**
 * @author FlowerStone
 * @title: UserMapper
 * @description:
 * @date 2022-04-08 22:08:42
 */
public interface UserMapper {

	/**
     * 查询所有记录
     *
     * @return 返回集合，没有返回空List
     */
	List<User> selectUserList(User user);


	/**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
	User selectUserById(Integer id);
	
	/**
     * 新增，插入所有字段
     *
     * @param user 新增的记录
     * @return 返回影响行数
     */
	Integer insertUser(User user);
	
	
	/**
     * 修改
     *
     * @param user 修改的记录
     * @return 返回影响行数
     */
	Integer updateUser(User user);
	

	/**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
	Integer deleteUserById(Integer id);
    
    /**
     * 根据条件删除记录
     *
     * @param user}
     * @return 返回影响行数
     */
	Integer deleteUserByCondition(User user);

	/**
	 * 获取单个对象
	 *
	 * @param user
	 * @return
	 * @date 2022年04月24日 0024 19:53:12
	 */
    User getOne(User user);
}