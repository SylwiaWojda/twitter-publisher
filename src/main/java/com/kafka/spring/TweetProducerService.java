package com.kafka.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class TweetProducerService {

    @Autowired
    private KafkaTemplate<String, List<Tweet>> kafkaTemplate;

    public void sendTweetsToConsumer(List<Tweet> tweetsArray) {
        kafkaTemplate.send("tweet-topic", "tweets", tweetsArray);

        //tutaj update
        String resourceUrlUpdate
                = "http://localhost:8080//twitter/updateIsPublishFlagOnAllTweetsFromDb";

        final RestTemplate restTemplateUpdate = new RestTemplate();

        ResponseEntity<Tweet[]> responseUpdate =
                restTemplateUpdate.getForEntity(
                        resourceUrlUpdate,
                        com.kafka.spring.Tweet[].class);

    }
}
