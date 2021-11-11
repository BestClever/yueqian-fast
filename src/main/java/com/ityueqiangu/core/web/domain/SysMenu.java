package com.ityueqiangu.core.web.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FlowerStone
 * @date 2021-11-11 17:38:27
 */
@Data
public class SysMenu {

    /**
     * 菜单编号
     */
    private String id;

    /**
     * 父节点
     */
    private String parentId;

    /**
     * 标题
     */
    private String title;

    /**
     * 菜单类型
     */
    private String type;

    /**
     * 打 开 类 型
     */
    private String openType;

    /**
     * 图标
     */
    private String icon;

    /**
     * 跳转路径
     */
    private String href;

    /**
     * 子菜单
     */
    private List<SysMenu> children = new ArrayList<>();

    /**
     * 用于参数传递
     */
    private String param;
}
