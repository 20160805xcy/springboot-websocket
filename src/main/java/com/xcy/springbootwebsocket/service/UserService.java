package com.xcy.springbootwebsocket.service;

import com.xcy.springbootwebsocket.model.UserWeb;

import java.util.List;

/**
 * @author xcy
 * @date 2018/12/12 14:54
 * @description
 * @since V1.0.0
 */
public interface UserService {
    UserWeb logIn(UserWeb userWeb);


    List<UserWeb> getAllUser();
}
