package com.it.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
public class SpringBootController {

    @RequestMapping("/getName")
    public Object getName(){

        System.out.println("Name");

        return "getName";
    }
}
