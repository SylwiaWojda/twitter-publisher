package com.kafka.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class UserProducerService {

    @Autowired
    private KafkaTemplate<String, Tweet[]> kafkaTemplate;

//    public void sendUserData(User user) {
//        kafkaTemplate.send("user-topic", user.getName(), user);
//    }


    public void sendTweetsToConsumer(Tweet[] tweetsArray) {
        kafkaTemplate.send("user-topic", "tweets", tweetsArray);
    }
}
