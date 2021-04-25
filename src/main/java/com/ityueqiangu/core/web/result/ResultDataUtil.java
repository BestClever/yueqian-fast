package com.ityueqiangu.core.web.result;


import com.ityueqiangu.core.exception.BaseInfoInterface;
import com.ityueqiangu.core.exception.BizException;

/**
 *  ResponseDataUtil:返回的工具类,主要是方便返回的写法
 *  * 单纯的返回工具类,直接放在这个包
 */
public class ResultDataUtil {


    /**
     * 返回成功描述和数据详情
     * @param baseInfoInterface
     * @return
     */
    public static ResultInfo createSuccess(BaseInfoInterface baseInfoInterface){
        return new ResultInfo().setSuccess(true).setCode(baseInfoInterface.getResultCode()).setMsg(baseInfoInterface.getResultMsg());
    }


    /**
     * 返回失败 使用枚举类的方式
     * @param baseInfoInterface
     * @return
     */
    public static ResultInfo createFail(BaseInfoInterface baseInfoInterface){
        return new ResultInfo().setSuccess(false).setCode(baseInfoInterface.getResultCode()).setMsg(baseInfoInterface.getResultMsg());
    }

    /**
     * 失败
     */
    public static ResultInfo createFail(Integer code, String message) {
        return new ResultInfo().setSuccess(false).setCode(code).setMsg(message);
    }


    /**
     * 创建查询结果信息 专门为 数据表格设计
     * @param pageWrapper
     * @return
     */
    public static DataGridResultInfo createQueryResult(PageWrapper pageWrapper){
        return new DataGridResultInfo(pageWrapper.getTotal(),pageWrapper.getList());
    }

    /**ui
     * 抛出异常
     * @param baseInfoInterface
     * @throws
     */
    public static void throwExcepion(BaseInfoInterface baseInfoInterface) throws BizException {
        throw new BizException(baseInfoInterface);
    }

}
