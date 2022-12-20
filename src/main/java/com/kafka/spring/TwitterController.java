package com.kafka.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/getTweetsFromDb")
public class TwitterController {

    @Autowired
    private TweetProducerService service;


    @GetMapping("/getAll")
    public void getAllTweets() {
        String fooResourceUrl
                = "http://localhost:8080//twitter/allTweetsFromDb";

        final RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<com.kafka.spring.Tweet[]> response =
                restTemplate.getForEntity(
                        fooResourceUrl,
                        com.kafka.spring.Tweet[].class);
        Tweet[] tweetsArray = response.getBody();

        service.sendTweetsToConsumer(tweetsArray);
    }

}