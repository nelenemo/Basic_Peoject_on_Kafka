package com.example.springkafka.controller;

import com.example.springkafka.model.User;
import com.example.springkafka.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @GetMapping("/all")
    public List<User> getAllUser() {
        List<User> users = userRepo.findAll();
        return users;
    }

    @PostMapping("/save")

        public User saveUser(@RequestBody User user) {
        user = userRepo.save(user);
        return user;
    }

}
