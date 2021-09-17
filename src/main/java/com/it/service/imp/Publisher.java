package com.it.service.imp;

import com.it.enums.RedisTopicEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
@Slf4j
public class Publisher {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void sendMsg(String msg){
        redisTemplate.convertAndSend(RedisTopicEnums.TOPIC_HEALTHY.getTopic(), "Message: " + msg +
                ";Time:" + Calendar.getInstance().getTime());
    }


}
