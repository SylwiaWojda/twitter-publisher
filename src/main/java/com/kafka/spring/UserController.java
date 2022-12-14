package com.kafka.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/getTweetsFromDb")
public class UserController {

    @Autowired
    private UserProducerService service;

    //todo: przeniesc do consumer, bo to consumer bedzie wyszykiwal po key work z bazy
//    @GetMapping("/filterBy/{searchWord}")
//    public void sendUserData(@PathVariable String searchWord) {
//        User user = new User();
//        user.setAge(123);
//        user.setName(searchWord);
//        service.sendUserData(user);
//    }

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