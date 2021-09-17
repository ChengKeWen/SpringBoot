package com.it.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.service.Subscriber;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;
import java.util.List;

@Configuration
public class RedisConfig {

    @Autowired
    ObjectMapper objectMapper;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * DependencyDescriptor
     * 重点
     * 首先判断注入的类型，如果是数组、Collection、Map，则注入的是元素数据，即查找与元素类型相同的Bean，注入到集合中。
     * 强调下Map类型，Map的 key 为Bean的 name，value 为 与定义的元素类型相同的Bean。
     *将所有相同类型（实现了同一个接口）的Bean，一次性注入到集合类型中，具体实现查看spring源码
     *
     * 获取Subscriptor接口所有的实现类
     * 注入所有实现了接口的Bean
     * 将所有的配置消息接收处理类注入进来，那么消息接收处理类里面的注解对象也会注入进来
     */
    @Autowired
    private transient List<Subscriber> subscriptorList;

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

        //创建一个消息监听对象
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        //将监听对象放入到容器中
        container.setConnectionFactory(connectionFactory);

        if (this.subscriptorList != null && this.subscriptorList.size() > 0) {
            for (Subscriber subscriber : this.subscriptorList) {

                if (subscriber == null || StringUtils.isBlank(subscriber.getTopic())) {
                    continue;
                }
                //一个订阅者对应一个主题通道信息
                container.addMessageListener(subscriber, new PatternTopic(subscriber.getTopic()));
            }
        }

        return container;
    }
}
