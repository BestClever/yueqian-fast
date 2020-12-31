package com.ityueqiangu.framework.shiro.service;

import cn.hutool.extra.pinyin.PinyinUtil;
import com.ityueqiangu.common.constant.Constants;
import com.ityueqiangu.common.constant.ShiroConstants;
import com.ityueqiangu.common.constant.UserConstants;
import com.ityueqiangu.common.exception.BusinessException;
import com.ityueqiangu.common.utils.DateUtils;
import com.ityueqiangu.common.utils.MessageUtils;
import com.ityueqiangu.common.utils.ServletUtils;
import com.ityueqiangu.common.utils.StringUtils;
import com.ityueqiangu.framework.manager.AsyncManager;
import com.ityueqiangu.framework.manager.factory.AsyncFactory;
import com.ityueqiangu.project.system.user.domain.User;
import com.ityueqiangu.project.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 注册校验方法
 *
 * @author Clever、xia
 */
@Component
public class RegisterService {
    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordService passwordService;

    /**
     * 注册
     */
    public String register(User user) {
        String loginName = PinyinUtil.getPinyin(user.getUserName(),"");
        String password = user.getPassword();
        User resultUser = userService.selectUserByLoginName(loginName);
        if (StringUtils.isNotNull(resultUser)&& StringUtils.equals(loginName,resultUser.getLoginName())) {
            Long index = resultUser.getUserId() + 1;
            loginName = loginName+index;
        }
        if (!StringUtils.isNull(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
            throw new BusinessException("验证码错误");
        } else if (StringUtils.isEmpty(loginName)) {
            throw new BusinessException("用户名不能为空");
        } else if (StringUtils.isEmpty(password)) {
            throw new BusinessException("用户密码不能为空");
        } else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            throw new BusinessException("密码长度必须在5到20个字符之间");
        } else if (loginName.length() < UserConstants.USERNAME_MIN_LENGTH
                || loginName.length() > UserConstants.USERNAME_MAX_LENGTH) {
            throw new BusinessException("账户长度必须在2到20个字符之间");
        } else if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(loginName))) {
            throw new BusinessException("保存用户'" + loginName + "'失败，注册账号已存在");
        } else {
            user.setPwdUpdateDate(DateUtils.getNowDate());
            user.setLoginName(loginName);
            boolean regFlag = userService.registerUser(user);
            if (!regFlag) {
                throw new BusinessException("注册失败,请联系系统管理人员");
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.REGISTER, MessageUtils.message("user.register.success")));
            }
        }
        return loginName;
    }

    /**
     * 忘记密码修改
     *
     * @param user
     * @return
     */
    public void forgetPwd(User user) {
        //查询是否存在 该账号的用户
        User resultUser = userService.selectUserByLoginName(user.getLoginName());
        if (StringUtils.isNull(resultUser)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(user.getLoginName(), Constants.REGISTER, MessageUtils.message("ser.not.exists")));
            throw new BusinessException(MessageUtils.message("ser.not.exists"));
        }
        user.setPassword(passwordService.encryptPassword(resultUser.getLoginName(), user.getPassword(), resultUser.getSalt()));
        userService.updateUserByLoginName(user);
    }
}
