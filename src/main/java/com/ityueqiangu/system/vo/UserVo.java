package com.ityueqiangu.system.vo;

import com.ityueqiangu.system.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: clever、xia
 * @Date: 2019/12/2 8:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserVo extends User {

    private Integer page=1;
    private Integer limit=10;

    /**
     * 验证码
     */
    private String code;
}
