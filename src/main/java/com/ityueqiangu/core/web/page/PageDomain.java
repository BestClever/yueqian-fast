package com.ityueqiangu.core.web.page;

import cn.hutool.core.util.ObjectUtil;
import com.ityueqiangu.core.utils.StringUtils;
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
    private String isAsc = "asc";

    /** 分页参数合理化 */
    private Boolean reasonable = true;

    public String getOrderBy() {
        if (ObjectUtil.isEmpty(orderByColumn)) {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }

    public void setIsAsc(String isAsc) {
        if (StringUtils.isNotEmpty(isAsc)) {
            // 兼容前端排序类型
            if ("ascending".equals(isAsc)) {
                isAsc = "asc";
            } else if ("descending".equals(isAsc)) {
                isAsc = "desc";
            }
            this.isAsc = isAsc;
        }
    }

    public Boolean getReasonable() {
        if (StringUtils.isNull(reasonable)) {
            return Boolean.TRUE;
        }
        return reasonable;
    }
}
