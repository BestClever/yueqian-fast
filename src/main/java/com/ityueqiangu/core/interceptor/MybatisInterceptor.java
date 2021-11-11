package com.ityueqiangu.core.interceptor;

import com.ityueqiangu.common.utils.UserUtil;
import com.ityueqiangu.common.utils.reflect.ReflectUtils;
import com.ityueqiangu.core.web.ActiverUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

/**
 *  mybatis拦截器，自动注入创建人、创建时间、修改人、修改时间
 * @author xiacong
 * @date 2021-11-10 21:30:35
 */
@Slf4j
@Component
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        String sqlId = mappedStatement.getId();
        log.debug("------sqlId------" + sqlId);
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        log.debug("------sqlCommandType------" + sqlCommandType);

        if (parameter == null) {
            return invocation.proceed();
        }
        if (SqlCommandType.INSERT == sqlCommandType) {
            ActiverUser activerUser =  UserUtil.getCurrentUser();
            Field[] fields = ReflectUtils.getAllFields(parameter);
            for (Field field : fields) {
                log.debug("------field.name------" + field.getName());
                try {
                    if ("createUserId".equals(field.getName())) {
                        field.setAccessible(true);
                        Object local_createBy = field.get(parameter);
                        field.setAccessible(false);
                        if (local_createBy == null || local_createBy.equals("")) {
                            if (activerUser != null) {
                                // 登录人账号
                                field.setAccessible(true);
                                field.set(parameter, activerUser.getId());
                                field.setAccessible(false);
                            }
                        }
                    }
                    if ("createUserName".equals(field.getName())) {
                        field.setAccessible(true);
                        Object local_createBy = field.get(parameter);
                        field.setAccessible(false);
                        if (local_createBy == null || local_createBy.equals("")) {
                            if (activerUser != null) {
                                // 登录人账号名称
                                field.setAccessible(true);
                                field.set(parameter, activerUser.getLoginName());
                                field.setAccessible(false);
                            }
                        }
                    }
                    // 注入创建时间
                    if ("createTime".equals(field.getName())) {
                        field.setAccessible(true);
                        Object local_createDate = field.get(parameter);
                        field.setAccessible(false);
                        if (local_createDate == null || local_createDate.equals("")) {
                            field.setAccessible(true);
                            field.set(parameter, new Date());
                            field.setAccessible(false);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        if (SqlCommandType.UPDATE == sqlCommandType) {
            ActiverUser sysUser = UserUtil.getCurrentUser();
            Field[] fields = null;
            if (parameter instanceof MapperMethod.ParamMap) {
                MapperMethod.ParamMap<?> p = (MapperMethod.ParamMap<?>) parameter;
                //update-begin-author:scott date:20190729 for:批量更新报错issues/IZA3Q--
                if (p.containsKey("et")) {
                    parameter = p.get("et");
                } else {
                    parameter = p.get("param1");
                }
                //update-end-author:scott date:20190729 for:批量更新报错issues/IZA3Q-

                //update-begin-author:scott date:20190729 for:更新指定字段时报错 issues/#516-
                if (parameter == null) {
                    return invocation.proceed();
                }
                //update-end-author:scott date:20190729 for:更新指定字段时报错 issues/#516-

                fields = ReflectUtils.getAllFields(parameter);
            } else {
                fields = ReflectUtils.getAllFields(parameter);
            }

            for (Field field : fields) {
                log.debug("------field.name------" + field.getName());
                try {
                    if ("updateUserId".equals(field.getName())) {
                        //获取登录用户信息
                        if (sysUser != null) {
                            // 登录账号
                            field.setAccessible(true);
                            field.set(parameter, sysUser.getId());
                            field.setAccessible(false);
                        }
                    }
                    if ("updateUserName".equals(field.getName())) {
                        //获取登录用户信息
                        if (sysUser != null) {
                            // 登录账号名称
                            field.setAccessible(true);
                            field.set(parameter, sysUser.getLoginName());
                            field.setAccessible(false);
                        }
                    }
                    if ("updateTime".equals(field.getName())) {
                        field.setAccessible(true);
                        field.set(parameter, new Date());
                        field.setAccessible(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub
    }
}
