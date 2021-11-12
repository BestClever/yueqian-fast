package com.ityueqiangu.core.web.page;

import com.ityueqiangu.common.utils.StringUtils;
import lombok.Data;

/**
 * 分页数据
 *
 * @author FlowerStone
 */
@Data
public class PageDomain {
    /**
     * 当前记录起始索引
     */
    private Integer page;

    /**
     * 每页显示记录数
     */
    private Integer limit;

    /**
     * 排序列
     */
    private String orderByColumn;

    /**
     * 排序的方向desc或者asc
     */
    private String isAsc = "asc" ;

    public String getOrderBy() {
        if (StringUtils.isEmpty(orderByColumn)) {
            return "" ;
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }
}
