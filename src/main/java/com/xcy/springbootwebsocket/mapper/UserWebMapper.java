package com.xcy.springbootwebsocket.mapper;

import com.xcy.springbootwebsocket.model.UserWeb;
import com.xcy.springbootwebsocket.util.MyMapper;

public interface UserWebMapper extends MyMapper<UserWeb> {
    UserWeb selectUserByPassWord(UserWeb userWeb);
}