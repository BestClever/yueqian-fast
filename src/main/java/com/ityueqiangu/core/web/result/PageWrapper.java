package com.ityueqiangu.core.web.result;

import java.util.List;

/**
 * @author BestClever
 * @title: PageInfo
 * @projectName
 * @description: TODO
 * @date 2020-02-18 14:06
 */
public class PageWrapper {

    private Long total;

    private Long totalPage;

    private Long pageSize;

    private List<? extends  Object> list;

    private Long pageNum;


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public List<? extends Object> getList() {
        return list;
    }

    public void setList(List<? extends Object> list) {
        this.list = list;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }
}
