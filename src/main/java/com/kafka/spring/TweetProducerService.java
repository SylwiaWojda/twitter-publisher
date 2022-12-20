package com.kafka.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class TweetProducerService {

    @Autowired
    private KafkaTemplate<String, Tweet[]> kafkaTemplate;

    public void sendTweetsToConsumer(Tweet[] tweetsArray) {
        kafkaTemplate.send("tweet-topic", "tweets", tweetsArray);
    }
}
