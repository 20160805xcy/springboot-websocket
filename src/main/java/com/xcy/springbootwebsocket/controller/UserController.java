package com.xcy.springbootwebsocket.controller;

import com.xcy.springbootwebsocket.model.UserWeb;
import com.xcy.springbootwebsocket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;

/**
 * @author xcy
 * @date 2018/12/12 14:29
 * @description
 * @since V1.0.0
 */
@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;

    public static HashMap<String, Object> onlineUser = new HashMap<>();
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(UserWeb userWeb, Model model){
        UserWeb user = userService.logIn(userWeb);
        model.addAttribute("user", user);



        List<UserWeb> allUser = userService.getAllUser();
        model.addAttribute("userList", allUser);
        return "chatRoom";
    }

    @RequestMapping(value = "goLogIn",method = RequestMethod.GET)
    public String goLogIn(){
        return "login";
    }
}
