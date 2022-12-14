package com.kafka.spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/getTweetsFromDb")
public class UserController {

    @Autowired
    private UserProducerService service;

    @GetMapping("/filterBy/{searchWord}")
    public void sendUserData(@PathVariable String searchWord) {
        User user = new User();
        user.setAge(123);
        user.setName(searchWord);
        service.sendUserData(user);
    }
}