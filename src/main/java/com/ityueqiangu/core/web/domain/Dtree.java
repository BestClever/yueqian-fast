package com.ityueqiangu.core.web.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Dtree树结构实体类
 *
 * @author FlowerStone
 */
@Data
public class Dtree implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Integer id;

    /**
     * 节点父ID
     */
    private Integer parentId;

    /**
     * 节点标题
     */
    private String title;

    /**
     * 是否勾选
     */
    private String checkArr = "0";

    /**
     * 图标
     */
    private String iconClass;
}
