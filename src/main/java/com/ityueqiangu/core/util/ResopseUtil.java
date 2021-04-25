package com.ityueqiangu.core.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author BestClever
 * @title: ResopseUtil
 * @projectName roomManage
 * @description:  结果返回类
 * @date 2020-09-11 14:14
 */
public class ResopseUtil {

    public static void responseOutWithJson(HttpServletResponse response,
                                           Object responseObject) {
        //将实体对象转换为JSON Object转换
        JSONObject responseJSONObject = JSONUtil.parseObj(responseObject);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(responseJSONObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
