package com.ityueqiangu.system.service;

import com.ityueqiangu.core.web.vo.ActiverUser;

/**
 * @author Clever、xia
 * @title: UserService
 * @projectName teacher-evaluate-system
 * @description:
 * @date 2021-03-31 21:45
 */
public interface IUserService {

    ActiverUser login(ActiverUser activerUser);
}
