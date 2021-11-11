package com.ityueqiangu.core.web.domain;

import com.ityueqiangu.common.utils.StringUtils;

import java.util.HashMap;

/**
 * 操作消息提醒
 *
 * @author Clever、xia
 */
public class ResponseInfo extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type
    {
        /** 成功 */
        SUCCESS(200),
        /** 警告 */
        WARN(301),
        /** 错误 */
        ERROR(500);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }

    /**
     * 初始化一个新创建的 ResponseInfo 对象，使其表示一个空消息。
     */
    public ResponseInfo()
    {
    }

    /**
     * 初始化一个新创建的 ResponseInfo 对象
     *
     * @param type 状态类型
     * @param msg 返回内容
     */
    public ResponseInfo(Type type, String msg)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 ResponseInfo 对象
     *
     * @param type 状态类型
     * @param msg 返回内容
     * @param data 数据对象
     */
    public ResponseInfo(Type type, String msg, Object data)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 方便链式调用
     *
     * @param key 键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public ResponseInfo put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ResponseInfo success()
    {
        return ResponseInfo.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ResponseInfo success(Object data)
    {
        return ResponseInfo.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ResponseInfo success(String msg)
    {
        return ResponseInfo.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResponseInfo success(String msg, Object data)
    {
        return new ResponseInfo(Type.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResponseInfo warn(String msg)
    {
        return ResponseInfo.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResponseInfo warn(String msg, Object data)
    {
        return new ResponseInfo(Type.WARN, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static ResponseInfo error()
    {
        return ResponseInfo.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResponseInfo error(String msg)
    {
        return ResponseInfo.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResponseInfo error(String msg, Object data)
    {
        return new ResponseInfo(Type.ERROR, msg, data);
    }
}
