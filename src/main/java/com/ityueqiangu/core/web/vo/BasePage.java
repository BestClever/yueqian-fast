package com.ityueqiangu.core.web.vo;

/**
 * @author BestClever
 * @title: BasePage
 * @projectName media-library-manage
 * @description: 分页 基础类
 * @date 2020-07-02 20:49
 */
public class BasePage {

    private Integer page = 0; //当前页码

    private Integer limit = 10;//每页数量

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
