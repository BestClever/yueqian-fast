package com.ityueqiangu.project.userinfo.service;


import com.ityueqiangu.project.system.dto.EditPasswordReqDto;
import com.ityueqiangu.project.userinfo.domain.User;

import java.util.List;

/**
 * @author FlowerStone
 * @title: UserService
 * @description:
 * @date 2022-04-08 22:08:42
 */
public interface IUserService {

    /**
     * 查询所有记录
     *
     * @return list集合
     */
    List<User> selectUserList(User user);


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回User
     */
    User selectUserById(Integer id);
	
    /**
     * 新增
     *
     * @param user 新增的记录
     * @return 返回ResultInfo
     */
    Integer insertUser(User user);
	
	
    /**
     * 修改，修改所有字段
     *
     * @param user 修改的记录
     * @return 返回ResultInfo
     */
    Integer updateUser(User user);
	
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    Integer deleteUserById(Integer id);

    /**
     * 获取单个对象
     *
     * @param user
     * @return
     * @date 2022年04月08日 0008 22:15:13
     */
    User getOne(User user);

    void updatePwd(EditPasswordReqDto editPasswordReqDto);
}