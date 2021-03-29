package com.ityueqiangu.system.controller;


import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ityueqiangu.core.common.DataGridView;
import com.ityueqiangu.core.common.ResultObj;
import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.core.utils.AppFileUtils;
import com.ityueqiangu.core.utils.ServletUtils;
import com.ityueqiangu.system.domain.Dept;
import com.ityueqiangu.system.domain.Role;
import com.ityueqiangu.system.domain.User;
import com.ityueqiangu.system.service.IDeptService;
import com.ityueqiangu.system.service.IRoleService;
import com.ityueqiangu.system.service.IUserService;
import com.ityueqiangu.system.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 9216 kB; (`deptid`) REFER `warehouse/sys_dept`(`id`) ON UPDATE CASC 前端控制器
 * </p>
 *
 * @author luoyi-
 * @since 2019-11-21
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IDeptService deptService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/add")
    public String add(){
        return "/system/user/add";
    }

    /**
     * 查询所有用户
     * @param userVo
     * @return
     */
    @RequestMapping("loadAllUser")
    @ResponseBody
    public DataGridView loadAllUser(UserVo userVo){
        IPage<User> page = new Page<User>(userVo.getPage(),userVo.getLimit());
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //根据用户登录名称以及用户名称模糊查询用户
        queryWrapper.like(StringUtils.isNotBlank(userVo.getName()),"loginname",userVo.getName()).or().eq(StringUtils.isNotBlank(userVo.getName()),"name",userVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(userVo.getAddress()),"address",userVo.getAddress());
        //查询系统用户
        queryWrapper.eq("type", Constants.USER_TYPE_NORMAL);
        queryWrapper.eq(userVo.getDeptid()!=null,"deptid",userVo.getDeptid());
        queryWrapper.orderByDesc("id");
        userService.page(page,queryWrapper);

        //将所有用户数据放入list中
        List<User> list = page.getRecords();
        for (User user : list) {
            Integer deptId = user.getDeptid();
            if (deptId!=null){
                //先从缓存中去取，如果缓存中没有就去数据库中取
                Dept one = deptService.getById(deptId);
                //设置user的部门名称
                user.setDeptname(one.getName());
            }
            Integer mgr = user.getMgr();
            if (mgr!=null&&mgr!=0){
                User one = userService.getById(mgr);
                //设置user的领导名称
                user.setLeadername(one.getName());
            }
        }
        return new DataGridView(page.getTotal(),list);
    }

    /**
     * 加载排序码
     * @return
     */
    @RequestMapping("loadUserMaxOrderNum")
    @ResponseBody
    public Map<String,Object> loadUserMaxOrderNum(){
        Map<String,Object> map = new HashMap<String,Object>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.orderByDesc("ordernum");
        IPage<User> page = new Page<>(1,1);
        List<User> list = userService.page(page,queryWrapper).getRecords();
        if (list.size()>0){
            map.put("value",list.get(0).getOrdernum()+1);
        }else {
            map.put("value",1);
        }
        return map;
    }

    /**
     * 根据部门ID查询用户
     * @param deptId
     * @return
     */
    @RequestMapping("loadUsersByDeptId")
    @ResponseBody
    public DataGridView loadUsersByDeptIp(Integer deptId){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq(deptId!=null,"deptid",deptId);
        queryWrapper.eq("available", Constants.AVAILABLE_TRUE);
        queryWrapper.eq("type", Constants.USER_TYPE_NORMAL);
        List<User> list = userService.list(queryWrapper);
        for (User user : list) {
            System.out.println(user.toString());
        }
        return new DataGridView(list);
    }

    /**
     * 把用户名转成拼音
     * @param username
     * @return
     */
    @RequestMapping("changeChineseToPinyin")
    @ResponseBody
    public Map<String,Object> changeChineseToPinyin(String username){
        Map<String,Object> map = new HashMap<String, Object>(16);
        if (null!=username){
//            map.put("value", PinyinUtils.getPingYin(username));
            map.put("value", PinyinUtil.getPinyin(username,""));
        }else {
            map.put("value","");
        }
        return map;
    }

    /**
     * 添加用户
     * @param userVo
     * @return
     */
    @RequestMapping("addUser")
    @ResponseBody
    public ResultObj addUser(UserVo userVo){
        try {
            //设置类型
            userVo.setType(Constants.USER_TYPE_NORMAL);
            //设置盐
            String salt = IdUtil.simpleUUID().toUpperCase();
            userVo.setSalt(salt);
            //设置默认密码
            userVo.setPwd(new Md5Hash(Constants.USER_DEFAULT_PWD,salt,2).toString());
            //设置用户默认头像
            userVo.setImgpath(Constants.DEFAULT_IMG_USER);
            userService.save(userVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 根据id查询一个用户
     * @param id  领导的id
     * @return
     */
    @RequestMapping("loadUserById")
    @ResponseBody
    public DataGridView loadUserById(Integer id){
        return new DataGridView(userService.getById(id));
    }

    /**
     * 修改用户
     * @param userVo
     * @return
     */
    @RequestMapping("updateUser")
    @ResponseBody
    public ResultObj updateUser(UserVo userVo){
        try {
            userService.updateById(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("deleteUser/{id}")
    @ResponseBody
    public ResultObj deleteUser(@PathVariable("id") Integer id){
        try {
            userService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据用户ID查询当前用户是否是其他用户的直属领导
     * @param userId
     * @return
     */
    @RequestMapping("queryMgrByUserId")
    @ResponseBody
    public ResultObj queryMgrByUserId(Integer userId){
        Boolean isMgr = userService.queryMgrByUserId(userId);
        if (isMgr){
            return ResultObj.DELETE_ERROR_NEWS;
        }else {
            return ResultObj.DELETE_QUERY;
        }
    }

    /**
     * 重置用户密码
     * @param id
     * @return
     */
    @RequestMapping("resetPwd/{id}")
    @ResponseBody
    public ResultObj resetPwd(@PathVariable("id") Integer id){
        try {
            User user = new User();
            user.setId(id);
            //设置盐  32位(大写英文字母(A-Z)加数字(0-9))
            String salt = IdUtil.simpleUUID().toUpperCase();
            user.setSalt(salt);
            //设置密码
            user.setPwd(new Md5Hash(Constants.USER_DEFAULT_PWD,salt,2).toString());
            userService.updateById(user);
            return ResultObj.RESET_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 根据用户id查询角色并选中已拥有的角色
     * @param id 用户id
     * @return
     */
    @RequestMapping("initRoleByUserId")
    @ResponseBody
    public DataGridView initRoleByUserId(Integer id){
        //1.查询所有可用的角色
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constants.AVAILABLE_TRUE);
        List<Map<String, Object>> listMaps = roleService.listMaps(queryWrapper);
        //2.查询当前用户拥有的角色ID集合
        List<Integer> currentUserRoleIds = roleService.queryUserRoleIdsByUid(id);
        for (Map<String, Object> map : listMaps) {
            Boolean LAY_CHECKED=false;
            Integer roleId = (Integer) map.get("id");
            for (Integer rid : currentUserRoleIds) {
                //如果当前用户已有该角色，则让LAY_CHECKED为true。LAY_CHECKED为true时，复选框选中
                if (rid.equals(roleId)){
                    LAY_CHECKED=true;
                    break;
                }
            }
            map.put("LAY_CHECKED",LAY_CHECKED);
        }
        return new DataGridView(Long.valueOf(listMaps.size()),listMaps);
    }

    /**
     * 保存用户和角色的关系
     * @param uid 用户的ID
     * @param ids 用户拥有的角色的ID的数组
     * @return
     */
    @RequestMapping("saveUserRole")
    @ResponseBody
    public ResultObj saveUserRole(Integer uid,Integer[] ids){
        try {
            userService.saveUserRole(uid,ids);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }

    /**
     * 修改用户的密码
     * @param oldPassword  用户的原密码
     * @param newPwdOne     用户第一次输入的新密码
     * @param newPwdTwo     用户第二次输入的新密码
     * @return
     */
    @RequestMapping("changePassword")
    @ResponseBody
    public ResultObj changePassword(String oldPassword,String newPwdOne,String newPwdTwo){
        //1.先通过session获得当前用户的ID
        User user =(User) ServletUtils.getSession().getAttribute("user");
        //2.将oldPassword加盐并散列两次在和数据库中的密码进行对比
        Integer userId = user.getId();
        User user1 = userService.getById(userId);
        //2.1获得该用户的盐
        String salt = user1.getSalt();
        //2.2通过用户输入的原密码，从数据库中查出的盐，散列次数生成新的旧密码
        String oldPassword2 = new Md5Hash(oldPassword,salt, Constants.HASHITERATIONS).toString();
        if (oldPassword2.equals(user1.getPwd())){
            if (newPwdOne.equals(newPwdTwo)){
                //3.生成新的密码
                String newPassword = new Md5Hash(newPwdOne,salt, Constants.HASHITERATIONS).toString();
                user1.setPwd(newPassword);
                userService.updateById(user1);
                return ResultObj.UPDATE_SUCCESS;
            }else {
                return ResultObj.UPDATE_ERROR;
            }
        }else {
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 返回当前已登录的user
     * @return
     */
    @RequestMapping("getNowUser")
    @ResponseBody
    public User getNowUser(){
        //1.获取当前session中的user
        User user = (User) ServletUtils.getSession().getAttribute("user");
        System.out.println("*****************************************");
        System.out.println(user);
        return user;
    }


    /**
     * 修改用户
     * @param userVo
     * @return
     */
    @RequestMapping("updateUserInfo")
    public ResultObj updateUserInfo(UserVo userVo){
        try {
            //用户头像不是默认图片
            if (!(userVo.getImgpath()!=null&&userVo.getImgpath().equals(Constants.DEFAULT_IMG_GOODS))){
                if (userVo.getImgpath().endsWith("_temp")){
                    String newName = AppFileUtils.renameFile(userVo.getImgpath());
                    userVo.setImgpath(newName);
                    //删除原先的图片
                    String oldPath = userService.getById(userVo.getId()).getImgpath();
                    AppFileUtils.removeFileByPath(oldPath);
                    //获取存储在session中的user并重新设置user中的图片地址
                    User user = (User) ServletUtils.getSession().getAttribute("user");
                    user.setImgpath(newName);
                    //重新设置user
                    ServletUtils.getSession().setAttribute("user",user);
                }
            }
            userService.updateById(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

}

