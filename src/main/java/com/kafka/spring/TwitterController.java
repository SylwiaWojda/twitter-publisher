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
        String resourceUrl
                = "http://localhost:8080//twitter/allTweetsFromDb";

        final RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<com.kafka.spring.Tweet[]> response =
                restTemplate.getForEntity(
                        resourceUrl,
                        com.kafka.spring.Tweet[].class);
        Tweet[] tweetsArray = response.getBody();


        String resourceUrlUpdate
                = "http://localhost:8080//twitter/updateIsPublishFlagOnAllTweetsFromDb";

        final RestTemplate restTemplateUpdate = new RestTemplate();

        ResponseEntity<com.kafka.spring.Tweet[]> responseUpdate =
                restTemplateUpdate.getForEntity(
                        resourceUrlUpdate,
                        com.kafka.spring.Tweet[].class);

        service.sendTweetsToConsumer(tweetsArray);


        //update all tweets on db as send


    }

}