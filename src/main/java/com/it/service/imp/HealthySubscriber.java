package com.it.service.imp;

import com.it.enums.RedisTopicEnums;
import com.it.service.Subscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HealthySubscriber implements Subscriber {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public String getTopic() {
        return RedisTopicEnums.TOPIC_HEALTHY.getTopic();
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        String deviceIds =  (String)redisTemplate.getValueSerializer().deserialize(message.getBody());
        log.info(">>订阅消息，设备健康异常编号: {}",deviceIds);



    }
}
