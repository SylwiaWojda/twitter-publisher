package com.kafka.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TwitterApi {
    public static void main(String[] args) {

        SpringApplication.run(TwitterApi.class, args);
        System.out.println("Hello world!");
    }
}