package com.ityueqiangu.core.web.page;

import com.ityueqiangu.common.constant.Constants;
import com.ityueqiangu.common.utils.ServletUtils;

/**
 * 表格数据处理
 *
 * @author FlowerStone
 */
public class TableSupport {
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPage(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        pageDomain.setLimit(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }

}
