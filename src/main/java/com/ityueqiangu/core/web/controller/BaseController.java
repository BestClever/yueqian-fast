package com.ityueqiangu.core.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ityueqiangu.common.utils.DateUtils;
import com.ityueqiangu.common.utils.StringUtils;
import com.ityueqiangu.common.utils.sql.SqlUtil;
import com.ityueqiangu.core.web.domain.ResponseInfo;
import com.ityueqiangu.core.web.domain.ResponseInfo.Type;
import com.ityueqiangu.core.web.page.PageDomain;
import com.ityueqiangu.core.web.page.TableDataInfo;
import com.ityueqiangu.core.web.page.TableSupport;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * web层通用数据处理
 *
 * @author FlowerStone
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
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPage();
        Integer pageSize = pageDomain.getLimit();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            Boolean reasonable = pageDomain.getReasonable();
            PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
        }
    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setData(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected ResponseInfo toAjax(int rows) {
        return rows > 0 ? success() : error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected ResponseInfo toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public ResponseInfo success() {
        return ResponseInfo.success();
    }

    /**
     * 返回失败消息
     */
    public ResponseInfo error() {
        return ResponseInfo.error();
    }

    /**
     * 返回成功消息
     */
    public ResponseInfo success(String message) {
        return ResponseInfo.success(message);
    }

    /**
     * 返回失败消息
     */
    public ResponseInfo error(String message) {
        return ResponseInfo.error(message);
    }

    /**
     * 返回错误码消息
     */
    public ResponseInfo error(Type type, String message) {
        return new ResponseInfo(type, message);
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }

//    public User getSysUser()
//    {
//        return ShiroUtils.getSysUser();
//    }
//
//    public void setSysUser(User user)
//    {
//        ShiroUtils.setSysUser(user);
//    }
//
//    public Long getUserId()
//    {
//        return getSysUser().getUserId();
//    }
//
//    public String getLoginName()
//    {
//        return getSysUser().getLoginName();
//    }
}
