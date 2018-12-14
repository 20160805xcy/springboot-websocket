package com.xcy.springbootwebsocket.pojo;

/**
 * @author xcy
 * @date 2018/12/11 14:55
 * @description
 * @since V1.0.0
 */
public class Message {
    private String message;

    private String userId;

    private String userName;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
