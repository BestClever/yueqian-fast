package com.ityueqiangu.core.mybatis;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * @author Clever、xia
 * @title: FastjsonTypeHandler
 * @projectName teacher-evaluate-system
 * @description:
 * @date 2021-04-12 11:26
 */
@Slf4j
@MappedTypes({Object.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class FastjsonTypeHandler extends AbstractJsonTypeHandler<Object> {

    private final Class<?> type;

    public FastjsonTypeHandler(Class<?> type) {
        if (log.isTraceEnabled()) {
            log.trace("FastjsonTypeHandler(" + type + ")");
        }
        this.type = type;
    }

    @Override
    protected Object parse(String json) {
        return JSON.parseObject(json, type);
    }

    @Override
    protected String toJson(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty);
    }
}
