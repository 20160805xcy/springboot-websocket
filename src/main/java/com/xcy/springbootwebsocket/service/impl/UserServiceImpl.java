package com.xcy.springbootwebsocket.service.impl;

import com.xcy.springbootwebsocket.mapper.UserWebMapper;
import com.xcy.springbootwebsocket.model.UserWeb;
import com.xcy.springbootwebsocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xcy
 * @date 2018/12/12 14:56
 * @description
 * @since V1.0.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserWebMapper userWebMapper;
    @Override
    public UserWeb logIn(UserWeb userWeb) {
        UserWeb user = userWebMapper.selectUserByPassWord(userWeb);
        return user;
    }

    @Override
    public List<UserWeb> getAllUser() {
        return userWebMapper.selectAll();
    }
}
