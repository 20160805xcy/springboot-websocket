package com.xcy.springbootwebsocket.util;

/**
 * @author xcy
 * @date 2018/09/10 13:50
 * @description MyMapper
 * @since V1.0.0
 */

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
