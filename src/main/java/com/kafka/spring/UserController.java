package com.kafka.spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/a")
public class UserController {

    @Autowired
    private UserProducerService service;

    @GetMapping("/b")
    public void sendUserData() {
        User user = new User();
        user.setAge(123);
        user.setName("Ania");
        service.sendUserData(user);
    }
}