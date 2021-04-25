package com.ityueqiangu.system.service.impl;

import com.ityueqiangu.system.domain.SysUser;
import com.ityueqiangu.system.mapper.SysUserMapper;
import com.ityueqiangu.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Clever、xia
 * @title: SysUserServiceImpl
 * @description:
 * @date 2021-04-03 19:52:56
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询分页记录
     *
     * @return 返回集合，没有返回空List
     */
     public List<SysUser> selectSysUserList(SysUser sysUser) {
       return sysUserMapper.selectSysUserList(sysUser);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回ResultInfo
     */
    public SysUser selectSysUserById(Integer id) {
    	return sysUserMapper.selectSysUserById(id);
    }
	
    /**
     * 新增
     *
     * @param sysUser 新增的记录
     * @return 返回ResultInfo
     */
    public Integer insertSysUser(SysUser sysUser) {
    	return sysUserMapper.insertSysUser(sysUser);
    }
	
	
    /**
     * 修改
     *
     * @param sysUser 修改的记录
     * @return 返回
     */
    public Integer updateSysUser(SysUser sysUser) {
    	return sysUserMapper.updateSysUser(sysUser);
    }
	
  
    /**
     * 删除记录
     *
     * @param id 主键
     * @return 返回
     */
    public Integer deleteSysUserById(Integer id) {
    	return sysUserMapper.deleteSysUserById(id);
    }

    @Override
    public SysUser selectSysUserByLoginName(SysUser sysUser) {
        return sysUserMapper.getOne(sysUser);
    }

    /**
     * 没有评价的学生
     * @param sysUser
     * @return
     */
    @Override
    public List<SysUser> notEvaluateList(SysUser sysUser) {
        return sysUserMapper.notEvaluateList(sysUser);
    }

}