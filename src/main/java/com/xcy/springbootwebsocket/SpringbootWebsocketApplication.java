package com.xcy.springbootwebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.xcy.springbootwebsocket.mapper"})
public class SpringbootWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebsocketApplication.class, args);
    }
}
