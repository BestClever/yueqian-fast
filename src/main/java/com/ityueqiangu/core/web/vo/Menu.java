package com.ityueqiangu.core.web.vo;

import com.ityueqiangu.core.util.StringUtils;
import com.ityueqiangu.system.domain.SysPermission;
import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BestClever
 * @title: Meun
 * @projectName book-manage
 * @description: TODO
 * @date 2020-11-01 15:25
 */
@Data
public class Menu {

    /*主键*/
    private Integer id;
    /*标题*/
    private String title;
    /*图标*/
    private String icon;
    /*url链接*/
    private String href;
    /*是否展开*/
    private boolean spread;
    /*目标*/
    private String target;
    /*子数组*/
    private List<Menu> children = new ArrayList<>();
    /*是否选中*/
    private String checkArr;
    /*父节点id*/
    private Integer parentId;


    public static ArrayList<Menu> adapterMenu(List<SysPermission> sysPermissions) {
        ArrayList<Menu> menus = new ArrayList<>();
        sysPermissions.stream().forEach(sysPermission -> {
            Menu menu = new Menu();
            menu.setId(sysPermission.getId());
            menu.setParentId(sysPermission.getParentId());
            menu.setHref(sysPermission.getPermissionUrl());
            menu.setIcon(sysPermission.getIcon());
            menu.setTitle(sysPermission.getPermissionName());
            menu.setSpread(StringUtils.equals("0", sysPermission.getIsSpread()) ? false : true);
            menu.setCheckArr(sysPermission.getCheckArr());
            menus.add(menu);
        });
        return menus;
    }


    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.ityueqiangu.core.web.vo.Menu");
        //获取 Person 类的所有方法信息
        Method[] method = clazz.getDeclaredMethods();
        for (Method m : method) {
            System.out.println("所有方法信息:"+m.toString());
        }
        //获取 Person 类的所有成员属性信息
        Field[] field = clazz.getDeclaredFields();
        for (Field f : field) {
            System.out.println("所有成员属性信息:"+f.toString());
        }
        //获取 Person 类的所有构造方法信息
        Constructor[] constructor = clazz.getDeclaredConstructors();
        for (Constructor c : constructor) {
            System.out.println("所有构造方法信息:"+c.toString());
        }
        System.out.println(clazz);
    }

}
