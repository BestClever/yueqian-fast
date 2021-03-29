package com.ityueqiangu.core.common;

import com.ityueqiangu.core.constant.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * @Author: 落亦-
 * @Date: 2019/11/21 21:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj extends HashMap<String, Object> {

    private Integer code;
    private String msg;

    public static final ResultObj LOGIN_SUCCESS=new ResultObj(Constants.OK,"登陆成功");
    public static final ResultObj LOGIN_ERROR_PASS=new ResultObj(Constants.ERROR,"用户名或密码错误");
    public static final ResultObj LOGIN_ERROR_CODE=new ResultObj(Constants.ERROR,"验证码错误");

    public static final ResultObj ADD_SUCCESS = new ResultObj(Constants.OK,"添加成功");
    public static final ResultObj ADD_ERROR = new ResultObj(Constants.ERROR,"添加失败");

    public static final ResultObj DELETE_SUCCESS = new ResultObj(Constants.OK,"删除成功");
    public static final ResultObj DELETE_ERROR = new ResultObj(Constants.ERROR,"删除失败");

    public static final ResultObj UPDATE_SUCCESS = new ResultObj(Constants.OK,"修改成功");
    public static final ResultObj UPDATE_ERROR = new ResultObj(Constants.ERROR,"修改失败");

    public static final ResultObj RESET_SUCCESS = new ResultObj(Constants.OK,"重置成功");
    public static final ResultObj RESET_ERROR = new ResultObj(Constants.ERROR,"重置失败");

    public static final ResultObj DISPATCH_SUCCESS = new ResultObj(Constants.OK,"分配成功");
    public static final ResultObj DISPATCH_ERROR = new ResultObj(Constants.ERROR,"分配失败");

    public static final ResultObj BACKINPORT_SUCCESS = new ResultObj(Constants.OK,"退货成功");
    public static final ResultObj BACKINPORT_ERROR = new ResultObj(Constants.ERROR,"退货失败");
    public static final ResultObj SYNCCACHE_SUCCESS = new ResultObj(Constants.OK,"同步缓存成功");

    public static final ResultObj DELETE_ERROR_NEWS = new ResultObj(Constants.ERROR,"删除用户失败，该用户是其他用户的直属领导，请先修改该用户的下属的直属领导，再进行删除操作");
    public static final ResultObj DELETE_QUERY = new ResultObj();
    

}
