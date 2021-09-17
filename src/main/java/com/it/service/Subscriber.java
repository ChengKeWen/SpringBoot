package com.it.service;

import org.springframework.data.redis.connection.MessageListener;

public interface Subscriber extends MessageListener {

    /**
     * 类型
     * @return
     */
    default String getType(){
        return this.getClass().getSimpleName();
    }

    /**
     *
     * 通道名称
     * @return
     */
    String getTopic();
}
