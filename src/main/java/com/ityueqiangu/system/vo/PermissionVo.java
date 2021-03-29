package com.ityueqiangu.system.vo;

import com.ityueqiangu.system.domain.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: clever、xia
 * @Date: 2019/11/22 15:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionVo extends Permission {

    private Integer page=1;
    private Integer limit=10;
}
