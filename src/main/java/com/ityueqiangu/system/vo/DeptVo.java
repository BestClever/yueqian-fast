package com.ityueqiangu.system.vo;

import com.ityueqiangu.system.domain.Dept;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: clever、xia
 * @Date: 2019/11/26 11:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DeptVo extends Dept {

    private Integer page=1;
    private Integer limit=10;

}
