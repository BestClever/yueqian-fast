package com.ityueqiangu.project.userinfo.controller;

import com.ityueqiangu.core.web.controller.BaseController;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.project.userinfo.domain.User;
import com.ityueqiangu.project.userinfo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FlowerStone
 * @title: UserController
 * @description:
 * @date 2022-04-08 22:08:42
 */
@Controller
@RequestMapping(value="/admin/userInfo")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;
    
    @GetMapping(value = "/index")
    public String index(){
        return "userinfo/index";
    }

    @GetMapping(value = "/add")
    public String add(){
        return "add";
    }

    @GetMapping(value = "/edit")
    public String edit(Integer id, ModelMap mmap){
        User user = userService.selectUserById(id);
        mmap.addAttribute("user",user);
        return "edit";
    }

    /**
     * 分页查询
     *
     * @return 分页数据
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user) {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }


    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 返回记录，没有返回null
     */
    @GetMapping("/getById")
    @ResponseBody
    public ResponseInfo getById(Integer id) {
        return ResponseInfo.success(userService.selectUserById(id));
    }    
     
    /**
     * 新增
     *
     * @param user 新增的记录
     * @return 返回影响行数
     */
    @PostMapping("/insert")
    @ResponseBody
    public ResponseInfo insert(@RequestBody User user) {
        return toAjax(userService.insertUser(user));
    }    
      
    /**
     * 修改
     *
     * @param user 修改的记录
     * @return 返回影响行数
     */
    @PostMapping("/update")
    @ResponseBody
    public ResponseInfo update(@RequestBody User user) {
        return toAjax(userService.updateUser(user));
    }
    
    /**
     * 删除记录
     *
     * @param id
     * @return 返回影响行数
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResponseInfo delete(Integer id) {
        return toAjax(userService.deleteUserById(id));
    }
    
}