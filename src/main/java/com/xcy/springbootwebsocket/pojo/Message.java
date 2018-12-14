package com.xcy.springbootwebsocket.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xcy
 * @date 2018/12/11 14:55
 * @description
 * @since V1.0.0
 */
@Getter
@Setter
public class Message {
    private String message;

    private String userId;

    private String userName;
}
