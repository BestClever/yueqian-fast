package com.ityueqiangu.core.web.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ityueqiangu.core.constant.Constants;
import com.ityueqiangu.core.util.DateUtils;
import com.ityueqiangu.core.util.ServletUtils;
import com.ityueqiangu.core.util.StringUtils;
import com.ityueqiangu.core.web.result.DataGridResultInfo;
import com.ityueqiangu.core.web.result.PageWrapper;
import com.ityueqiangu.core.web.result.ResultDataUtil;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * @author Clever、xia
 * @title: BaseController
 * @projectName art-exhibition-system
 * @description:
 * @date 2021-03-11 10:58
 */
public class BaseController {

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        Integer pageNum = ServletUtils.getParameterToInt(Constants.PAGE_NUM);
        Integer pageSize = ServletUtils.getParameterToInt(Constants.PAGE_SIZE);
        String orderByColumn = ServletUtils.getParameter(Constants.ORDER_BY_COLUMN);
        String isAsc = ServletUtils.getParameter(Constants.IS_ASC);
        if (StringUtils.isNull(pageNum)) {
            pageNum = 1;
        }
        if (StringUtils.isNull(pageSize)) {
            pageSize = 10;
        }
        if (StringUtils.isBlank(isAsc)) {
            isAsc = "asc";
        }
        String orderBy  = null;
        if (StringUtils.isNotBlank(orderByColumn)) {
            orderBy =  StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
        }
        PageHelper.startPage(pageNum, pageSize,orderBy);
    }


    /**
     * 响应请求分页数据
     */
    protected DataGridResultInfo getDataTable(List<?> list)
    {
        PageWrapper pageWrapper = new PageWrapper();
        pageWrapper.setList(list);
        pageWrapper.setTotal(new PageInfo<>(list).getTotal());
        return ResultDataUtil.createQueryResult(pageWrapper);
    }
}
