package com.ityueqiangu.system.service.impl;

import com.ityueqiangu.core.enums.CommonEnum;
import com.ityueqiangu.core.enums.SysFlagStatusEnum;
import com.ityueqiangu.core.exception.BizException;
import com.ityueqiangu.core.util.StringUtils;
import com.ityueqiangu.core.web.vo.ActiverUser;
import com.ityueqiangu.system.domain.SysUser;
import com.ityueqiangu.system.service.ISysUserService;
import com.ityueqiangu.system.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Clever、xia
 * @title: UserServiceImpl
 * @projectName teacher-evaluate-system
 * @description:
 * @date 2021-03-31 21:46
 */
@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public ActiverUser login(ActiverUser activerUser) {
        SysUser sysUser = new SysUser();
        sysUser.setLoginName(activerUser.getLoginName());
        sysUser.setUserType(activerUser.getUserType());
        SysUser resultUser = sysUserService.selectSysUserByLoginName(sysUser);
        if (StringUtils.isNull(resultUser)) {
            throw new BizException(CommonEnum.USER_NOT_EXIST);
        }
        if (!StringUtils.equals(resultUser.getPassword(), activerUser.getPassword())) {
            throw new BizException(CommonEnum.PASSWORD_ERROR);
        }
        if (StringUtils.equals(SysFlagStatusEnum.ONE.getKey(), resultUser.getIsDeleted())) {
            throw new BizException(CommonEnum.ACCOUNT_DISABLED);
        }
        BeanUtils.copyProperties(resultUser, activerUser);
        return activerUser;
    }
}
