package com.ityueqiangu.project.userinfo.service.impl;

import cn.hutool.system.UserInfo;
import com.ityueqiangu.core.utils.UserUtil;
import com.ityueqiangu.core.web.domain.ActiverUserInfo;
import com.ityueqiangu.project.system.dto.EditPasswordReqDto;
import com.ityueqiangu.project.userinfo.domain.User;
import com.ityueqiangu.project.userinfo.mapper.UserMapper;
import com.ityueqiangu.project.userinfo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FlowerStone
 * @title: UserServiceImpl
 * @description:
 * @date 2022-04-08 22:08:42
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
    public List<User> selectUserList(User user) {
        return userMapper.selectUserList(user);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public User selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    /**
     * 新增
     *
     * @param user 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertUser(User user) {
        return userMapper.insertUser(user);
    }


    /**
     * 修改
     *
     * @param user 修改的记录
     * @return 返回
     */
    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }


    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteUserById(Integer id) {
        return userMapper.deleteUserById(id);
    }

    /**
     * 获取单个对象
     *
     * @param user
     * @return
     * @date 2022年04月08日 0008 22:15:50
     */
    @Override
    public User getOne(User user) {
        return userMapper.getOne(user);
    }

    /**
     * 修改密码
     *
     * @param editPasswordReqDto
     * @return
     * @date 2022年04月10日 0010 18:33:28
     */
    @Override
    public void updatePwd(EditPasswordReqDto editPasswordReqDto) {
        //获取当前 用户信息
        ActiverUserInfo activerUserInfo = UserUtil.getCurrentUserInfo();
        // 获取当前用户
        User user = userMapper.selectUserById(activerUserInfo.getUser().getId());

        user.setPassword(editPasswordReqDto.getNewPassword());
        userMapper.updateUser(user);
    }

}