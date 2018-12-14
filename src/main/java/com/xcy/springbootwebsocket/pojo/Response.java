package com.xcy.springbootwebsocket.pojo;

/**
 * @author xcy
 * @date 2018/12/11 14:58
 * @description
 * @since V1.0.0
 */
public class Response {
    private String responseMessage;

    public Response(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
