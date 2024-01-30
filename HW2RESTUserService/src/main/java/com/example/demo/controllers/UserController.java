package com.example.demo.controllers;

import com.example.demo.services.RegistrationService;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private RegistrationService service;

    @GetMapping
    public List<User> userList() {
        return service.getDataProcessingService().getAllUsers();
    }

    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user) {
        service.processRegistration(user);
        return "User added from body!";
    }

    @PostMapping("/add/{name}/{age}/{email}")
    public String userAddFromParam(@PathVariable("name") String name,
                                   @PathVariable("age") int age,
                                   @PathVariable("email") String email) {
        service.processRegistration(name, age, email);
        return "User added from param!";
    }
}
