package com.it.controller;

import com.it.service.imp.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
public class SpringBootController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    Publisher publisher;

    @RequestMapping("/getName")
    public Object getName(){

        System.out.println("Name");
        redisTemplate.opsForValue().set("name","学习Redis鸭！");
        System.out.println(redisTemplate.opsForValue().get("name"));

        return "getName";
    }

    @RequestMapping("/sendMsg")
    @ResponseBody
    public String sendMsg(@RequestParam("msg") String msg){
        publisher.sendMsg(msg);
        return "发送成功！";
    }

}
