package com.ityueqiangu.project.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.core.exception.BusinessException;
import com.ityueqiangu.core.utils.UserUtil;
import com.ityueqiangu.core.web.domain.SysMenu;
import com.ityueqiangu.project.system.domain.SysUser;
import com.ityueqiangu.project.system.domain.SysUserRole;
import com.ityueqiangu.project.system.mapper.SysUserMapper;
import com.ityueqiangu.project.system.service.ISysResourceService;
import com.ityueqiangu.project.system.service.ISysUserRoleService;
import com.ityueqiangu.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author FlowerStone
 * @title: SysUserServiceImpl
 * @description:
 * @date 2021-11-10 21:46:24
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Autowired
    private ISysResourceService sysResourceService;

    @PostConstruct
    public void initUserInfo() {
        List<SysUser> sysUsers = sysUserMapper.selectSysUserList(null);
        //UserUtil.sysUserMap = sysUsers.stream().collect(Collectors.toMap(sysuser->{ return sysuser.getId()+"";}, sysUser -> sysUser));
        System.out.println("==============>系统用户初始化成功");
    }

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
        SysUser param = new SysUser();
        param.setUserName(sysUser.getUserName());
        //判断登录名是否存在 如果存在不让新增
        SysUser result = sysUserMapper.getOne(param);
        if (ObjectUtil.isNotEmpty(result)) {
            throw new BusinessException("用户存在，请换一个登录名称");
        }
        //密码进行加密
        String inputPwd = sysUser.getPassword();
        String newPassword = SecureUtil.md5(inputPwd);
        sysUser.setPassword(newPassword);
        sysUser.setIsEnable(Constants.YES);
        //插入用户表
        sysUserMapper.insertSysUser(sysUser);
        //将用户与角色的关系插入到 用户角色表中
        List<SysUserRole> sysUserRoles = new ArrayList<SysUserRole>();
        for (String roleId : StrUtil.split(sysUser.getRoleIds(), ",")) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(Integer.valueOf(roleId));
            sysUserRole.setUserId(sysUser.getId());
            sysUserRoles.add(sysUserRole);
        }
        return sysUserRoleService.batchInsert(sysUserRoles);
    }


    /**
     * 修改
     *
     * @param sysUser 修改的记录
     * @return 返回
     */
    public Integer updateSysUser(SysUser sysUser) {
        //删除 用户角色的关系 重新建立
        //1.删除用户角色的关系
        SysUserRole sysUserRoleParam = new SysUserRole();
        sysUserRoleParam.setUserId(sysUser.getId());
        sysUserRoleService.deleteSysUserRoleByCondition(sysUserRoleParam);
        //2.重新建立用户角色的关系
        List<SysUserRole> sysUserRoles = new ArrayList<SysUserRole>();
        for (String roleId : StrUtil.split(sysUser.getRoleIds(), ",")) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(Integer.valueOf(roleId));
            sysUserRole.setUserId(sysUser.getId());
            sysUserRoles.add(sysUserRole);
        }
        sysUserRoleService.batchInsert(sysUserRoles);
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

    /**
     * 查询单个系统用户对象
     *
     * @param sysUser
     * @return
     * @author FlowerStone
     * @date 2021年11月11日 0011 15:02:53
     */
    @Override
    public SysUser getOne(SysUser sysUser) {
        return sysUserMapper.getOne(sysUser);
    }

    /**
     * 根据条件修改 用户
     *
     * @param sysUser
     * @return
     * @author FlowerStone
     * @date 2021年11月14日 0014 9:27:41
     */
    @Override
    public Integer updateSysUserByCondtion(SysUser sysUser) {
        return sysUserMapper.updateSysUser(sysUser);
    }

    /**
     * 根据用户名获取菜单
     *
     * @param userName
     * @return
     * @author FlowerStone
     * @date 2021年11月15日 0015 22:22:07
     */
    @Override
    public List<SysMenu> getUserMenu(String userName) {
        List<SysMenu> sysMenus = sysResourceService.selectMenuByUsername(userName);
        //获取搜有的id
        List<String> ids = sysMenus.stream().map(SysMenu::getId).collect(Collectors.toList());
        List<SysMenu> results = sysMenus.stream()
                .filter(item -> !CollectionUtil.contains(ids, item.getParentId()))
                .map(item -> {
                    //递归查询子节点
                    item.setChildren(recursionFn(item, sysMenus));
                    return item;
                })
                .collect(Collectors.toList());
        List<SysMenu> menus = new ArrayList<>();
        //设置一个初始化的菜单 控制台 页面
        SysMenu initMenu = new SysMenu();
        initMenu.setId("9999999999");
        initMenu.setTitle("首页");
        initMenu.setIcon("layui-icon layui-icon-console");
        initMenu.setType("1");
        initMenu.setOpenType("_iframe");
        initMenu.setHref("/admin/console");
        menus.add(initMenu);
        menus.addAll(results);
        return menus;
    }

    /**
     * 递归查询子节点
     *
     * @param root 根节点
     * @param alls 递归值
     * @return
     * @author FlowerStone
     * @date 2021年11月15日 0015 22:26:05
     */
    private List<SysMenu> recursionFn(SysMenu root, List<SysMenu> alls) {
        List<SysMenu> children = alls.stream()
                .filter(param -> ObjectUtil.equal(param.getParentId(), root.getId()))
                .map(item -> {
                    item.setChildren(recursionFn(item, alls));
                    return item;
                })
                .collect(Collectors.toList());
        return children;
    }

}