package com.xcy.springbootwebsocket.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author xcy
 * @Description
 * @Date 2018/12/15 12:42
 * @Version 1.0
 */
@Getter
@Setter
public class ServerInfo {
    //可用处理器
    private Integer processors;
    //空闲内存大小
    private Long freeMemory;
    //最大内存
    private Long maxMemory;

    public ServerInfo(Integer processors, Long freeMemory, Long maxMemory) {
        this.processors = processors;
        this.freeMemory = freeMemory;
        this.maxMemory = maxMemory;
    }

    @Override
    public String toString() {
        return "ServerInfo{" +
                "processors=" + processors +
                ", freeMemory=" + freeMemory +
                ", maxMemory=" + maxMemory +
                '}';
    }
}
