package com.ityueqiangu.core.web.vo;

import com.ityueqiangu.core.util.StringUtils;
import com.ityueqiangu.system.domain.SysPermission;
import lombok.Data;

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

}
