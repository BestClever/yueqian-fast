package com.ityueqiangu.project.system.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.ityueqiangu.common.constant.Constants;
import com.ityueqiangu.common.exception.BusinessException;
import com.ityueqiangu.common.utils.UserUtil;
import com.ityueqiangu.core.web.ActiverUser;
import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.project.system.domain.SysRole;
import com.ityueqiangu.project.system.domain.SysUser;
import com.ityueqiangu.project.system.dto.EditPasswordReqDto;
import com.ityueqiangu.project.system.service.ISysRoleService;
import com.ityueqiangu.project.system.service.ISysUserRoleService;
import com.ityueqiangu.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @RequestMapping(value = "/index")
    public String main() {
        return "system/user/index";
    }

    @RequestMapping(value = "/add")
    public String add(ModelMap mmap) {
        //获取有效的角色
        SysRole sysRole = new SysRole();
        sysRole.setIsEnable(Constants.SUCCESS);
        List<SysRole> sysRoles = sysRoleService.selectSysRoleList(sysRole);
        mmap.addAttribute("sysRoles", sysRoles);
        return "system/user/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Integer id, ModelMap mmap) {
        SysUser sysUser = sysUserService.selectSysUserById(id);
        List<SysRole> sysRoles = sysUserRoleService.getUserRole(id);
        mmap.addAttribute("sysRoles", sysRoles.stream().sorted(Comparator.comparing(SysRole::getSort)).collect(Collectors.toList()));
        mmap.addAttribute("sysUser", sysUser);
        return "system/user/edit";
    }

    /**
     * 个人资料
     *
     * @param mmap
     * @return
     * @author FlowerStone
     * @date 2021年11月18日 0018 14:48:40
     */
    @GetMapping(value = "/center")
    public String center(ModelMap mmap) {
        ActiverUser currentUser = UserUtil.getCurrentUser();
        mmap.addAttribute("userInfo", currentUser.getUserInfo());
        return "system/user/center";
    }

    /**
     * 更换头像
     *
     * @return
     * @author FlowerStone
     * @date 2021年11月18日 0018 16:06:55
     */
    @GetMapping(value = "/profile")
    public String profile() {
        return "system/user/profile";
    }

    /**
     * 修改密码
     *
     * @return
     * @author FlowerStone
     * @date 2021年11月18日 0018 17:38:33
     */
    @GetMapping(value = "/editPassword")
    public String editPassword() {
        return "system/user/password";
    }


    /**
     * 分页查询
     *
     * @return 分页数据
     */
    @RequestMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUser sysUser) {
        startPage();
        List<SysUser> list = sysUserService.selectSysUserList(sysUser);
        return getDataTable(list);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @RequestMapping("/getById")
    @ResponseBody
    public ResponseInfo getById(Integer id) {
        return ResponseInfo.success(sysUserService.selectSysUserById(id));
    }

    /**
     * 新增
     *
     * @param sysUser 新增的记录
     * @return 返回影响行数
     */
    @RequestMapping("/insert")
    @ResponseBody
    public ResponseInfo insert(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.insertSysUser(sysUser));
    }

    /**
     * 修改
     *
     * @param sysUser 修改的记录
     * @return 返回影响行数
     */
    @RequestMapping("/update")
    @ResponseBody
    public ResponseInfo update(@RequestBody SysUser sysUser) {
        return toAjax(sysUserService.updateSysUser(sysUser));
    }

    /**
     * 修改用户信息
     *
     * @param sysUser
     * @return
     * @author FlowerStone
     * @date 2021年11月18日 0018 21:36:31
     */
    @RequestMapping("/updateInfo")
    @ResponseBody
    public ResponseInfo updateInfo(@RequestBody SysUser sysUser) {
        ActiverUser<SysUser> userInfo = UserUtil.getCurrentUser();
        sysUser.setId(userInfo.getUserInfo().getId());
        sysUserService.updateSysUserByCondtion(sysUser);
        //重新刷新
        SysUser result = sysUserService.selectSysUserById(userInfo.getUserInfo().getId());
        userInfo.setUserInfo(result);
        UserUtil.refreshUser(userInfo);
        return ResponseInfo.success("更新成功！");
    }

    /**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseInfo delete(@RequestBody Integer id) {
        return toAjax(sysUserService.deleteSysUserById(id));
    }

    /**
     * 判断用户是否存在
     *
     * @param sysUser
     * @return
     * @author FlowerStone
     * @date 2021年11月12日 0012 21:04:07
     */
    @RequestMapping(value = "/existUser")
    @ResponseBody
    public ResponseInfo existUser(SysUser sysUser) {
        SysUser result = sysUserService.getOne(sysUser);
        if (ObjectUtil.isEmpty(result)) {
            return ResponseInfo.success("用户不存在");
        } else {
            return ResponseInfo.error("用户存在");
        }
    }

    /**
     * 更新启用状态
     *
     * @param sysUser
     * @return
     * @author FlowerStone
     * @date 2021年11月18日 0018 16:52:17
     */
    @PostMapping(value = "/updateStatus")
    @ResponseBody
    public ResponseInfo updateStatus(SysUser sysUser) {
        sysUserService.updateSysUserByCondtion(sysUser);
        return ResponseInfo.success("启用成功！");
    }

    /**
     * 修改头像
     *
     * @param sysUser
     * @return
     * @author FlowerStone
     * @date 2021年11月18日 0018 16:52:55
     */
    @PostMapping(value = "/updateAvatar")
    @ResponseBody
    public ResponseInfo updateAvatar(SysUser sysUser) {
        ActiverUser<SysUser> currentUser = UserUtil.getCurrentUser();
        sysUser.setId(currentUser.getUserInfo().getId());
        sysUserService.updateSysUserByCondtion(sysUser);
        //重新设置 session中的 值
        currentUser.getUserInfo().setAvatar(sysUser.getAvatar());
        UserUtil.refreshUser(currentUser);
        return ResponseInfo.success("更新成功！");
    }

    /**
     * 修改用户密码
     *
     * @param editPasswordReqDto
     * @return
     * @author FlowerStone
     * @date 2021年11月18日 0018 21:36:09
     */
    @PostMapping(value = "/editPassword")
    @ResponseBody
    public ResponseInfo updatePassword(@Validated EditPasswordReqDto editPasswordReqDto) {
        //先验证旧密码是否 正确
        ActiverUser<SysUser> currentUser = UserUtil.getCurrentUser();
        if (!StrUtil.equals(currentUser.getUserInfo().getPassword(), SecureUtil.md5(editPasswordReqDto.getOldPassword()))) {
            throw new BusinessException("旧密码输入错误！");
        }
        if (!StrUtil.equals(editPasswordReqDto.getNewPassword(), editPasswordReqDto.getConfirmPassword())) {
            throw new BusinessException("两次输入的密码不一样！");
        }
        //设置用户
        SysUser sysUser = new SysUser();
        sysUser.setId(currentUser.getUserInfo().getId());
        sysUser.setPassword(SecureUtil.md5(editPasswordReqDto.getNewPassword()));
        sysUserService.updateSysUserByCondtion(sysUser);
        return ResponseInfo.success("更新成功！重新登录生效！");
    }
}