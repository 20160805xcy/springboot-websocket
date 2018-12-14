package com.xcy.springbootwebsocket.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xcy
 * @date 2018/12/11 14:58
 * @description
 * @since V1.0.0
 */
@Getter
@Setter
public class Response {
    private String responseMessage;

    public Response(String responseMessage) {
        this.responseMessage = responseMessage;
    }


}
