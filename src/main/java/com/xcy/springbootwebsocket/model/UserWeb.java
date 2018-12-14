package com.xcy.springbootwebsocket.model;

import javax.persistence.*;

@Table(name = "user_web")
public class UserWeb {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 秘密
     */
    @Column(name = "pass_word")
    private String passWord;

    /**
     * 地址
     */
    private String address;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取秘密
     *
     * @return pass_word - 秘密
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * 设置秘密
     *
     * @param passWord 秘密
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
}