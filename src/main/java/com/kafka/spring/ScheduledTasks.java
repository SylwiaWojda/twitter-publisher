package com.kafka.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduledTasks {

    @Autowired
    private TweetProducerService service;

    public ScheduledTasks(TweetProducerService service) {
        this.service = service;
    }

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //milliseconds
    //10 minutes
    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void retrieveTweetsFromTwitterApi() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        //get only tweets that weren't sent. with isPublish false(0)
        String resourceUrl
                = "http://localhost:8080//twitter/allTweetsFromDb";

        final RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Tweet[]> response =
                restTemplate.getForEntity(
                        resourceUrl,
                        com.kafka.spring.Tweet[].class);
        Tweet[] tweetsArray = response.getBody();

        //filter
        List<Tweet> tweetsList = Arrays.asList(tweetsArray);

        List<Tweet> tweetsWithFlagFalse = tweetsList
                .stream()
                .filter(c -> c.getPublish() == Boolean.FALSE)
                .collect(Collectors.toList());

        service.sendTweetsToConsumer(tweetsWithFlagFalse);
    }
}
