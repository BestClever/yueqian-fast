package com.ityueqiangu.core.interceptor;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ityueqiangu.core.utils.OConvert;
import com.ityueqiangu.core.utils.UserUtil;
import com.ityueqiangu.core.web.domain.ActiverUser;
import com.ityueqiangu.core.web.domain.ActiverUserInfo;
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
 * mybatis拦截器，自动注入创建人、创建时间、修改人、修改时间
 *
 * @author FlowerStone
 * @date 2021-11-10 21:30:35
 */
@Slf4j
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
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
            ActiverUser activerUser = UserUtil.getCurrentUser();
            if (ObjectUtil.isNull(activerUser)) {
                activerUser = new ActiverUser();
                ActiverUserInfo activerUserInfo = UserUtil.getCurrentUserInfo();
                if (ObjectUtil.isNotEmpty(activerUserInfo)) {
                    activerUser.setLoginName(activerUserInfo.getLoginName());
                }
            }
            Field[] fields = OConvert.getAllFields(parameter);
            for (Field field : fields) {
                log.debug("------field.name------" + field.getName());
                try {
                    if ("createBy".equals(field.getName())) {
                        field.setAccessible(true);
                        Object local_createBy = field.get(parameter);
                        field.setAccessible(false);
                        if (local_createBy == null || local_createBy.equals("")) {
                            if (StrUtil.isNotBlank(activerUser.getLoginName())) {
                                // 登录人账号
                                field.setAccessible(true);
                                field.set(parameter, StrUtil.isBlank(activerUser.getLoginName()) ? "noLogin" : activerUser.getLoginName());
                                field.setAccessible(false);
                            }
                        }
                    }
                    if ("createUserName".equals(field.getName())) {
                        field.setAccessible(true);
                        Object local_createBy = field.get(parameter);
                        field.setAccessible(false);
                        if (ObjectUtil.isEmpty(local_createBy)) {
                            if (StrUtil.isNotBlank(activerUser.getUserName())) {
                                // 登录人账号名称
                                field.setAccessible(true);
                                field.set(parameter, activerUser.getUserName());
                                field.setAccessible(false);
                            }
                        }
                    }
                    // 注入创建时间
                    if ("createTime".equals(field.getName())) {
                        field.setAccessible(true);
                        Object local_createDate = field.get(parameter);
                        field.setAccessible(false);
                        if (ObjectUtil.isEmpty(local_createDate)) {
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
            ActiverUser activerUser = UserUtil.getCurrentUser();
            if (ObjectUtil.isNull(activerUser)) {
                activerUser = new ActiverUser();
                ActiverUserInfo activerUserInfo = UserUtil.getCurrentUserInfo();
                if (ObjectUtil.isNotEmpty(activerUserInfo)) {
                    activerUser.setLoginName(activerUserInfo.getLoginName());
                }
            }
            Field[] fields = null;
            if (parameter instanceof MapperMethod.ParamMap) {
                MapperMethod.ParamMap<?> p = (MapperMethod.ParamMap<?>) parameter;
                if (p.containsKey("et")) {
                    parameter = p.get("et");
                } else {
                    parameter = p.get("param1");
                }
                if (parameter == null) {
                    return invocation.proceed();
                }
                fields = OConvert.getAllFields(parameter);
            } else {
                fields = OConvert.getAllFields(parameter);
            }

            for (Field field : fields) {
                log.debug("------field.name------" + field.getName());
                try {
                    if ("updateBy".equals(field.getName())) {
                        //获取登录用户信息
                        if (ObjectUtil.isNotEmpty(activerUser)) {
                            // 登录人账号
                            field.setAccessible(true);
                            field.set(parameter, StrUtil.isBlank(activerUser.getLoginName()) ? "noLogin" : activerUser.getLoginName());
                            field.setAccessible(false);
                        }
                    }
                    if ("updateUserName".equals(field.getName())) {
                        // 登录账号名称
                        if (StrUtil.isNotBlank(activerUser.getUserName())) {
                            // 登录人账号名称
                            field.setAccessible(true);
                            field.set(parameter, StrUtil.isBlank(activerUser.getUserName()) ? "" : activerUser.getUserName());
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
