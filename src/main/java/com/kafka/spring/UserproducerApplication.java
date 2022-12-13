package com.kafka.spring;


import com.kafka.customserializers.Tweet;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static java.lang.Thread.sleep;

@Slf4j
@Service
public class UserproducerApplication {

    @Autowired
    private KafkaTemplate<String, Integer> kafkaTemplate;

    public void sendUserData(String name, int age) {
        kafkaTemplate.send("TweetTopic", name, age);
    }

}