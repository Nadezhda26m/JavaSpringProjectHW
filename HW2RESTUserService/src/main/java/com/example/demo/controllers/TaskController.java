package com.example.demo.controllers;

import com.example.demo.domain.User;
import com.example.demo.services.DataProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private DataProcessingService service;

    @GetMapping
    public List<String> getAllTasks() {
        List<String> tasks = new ArrayList<>();
        tasks.add("ageSort");
        tasks.add("filter/more/{age}");
        tasks.add("filter/less/{age}");
        tasks.add("calc");
        return tasks;
    }

    @GetMapping("/ageSort")
    public List<User> sortUsersByAge() {
        return service.sortUsersByAge();
    }

    @GetMapping("/filter/more/{age}")
    public List<User> filterUsersByAgeWhichMore(@PathVariable("age") int age) {
        return service.getUsersWithAgeMoreThan(age);
    }


    @GetMapping("/filter/less/{age}")
    public List<User> filterUsersByAgeWhichLess(@PathVariable("age") int age) {
        return service.getUsersWithAgeLessThan(age);
    }

    @GetMapping("/calc")
    public double calculateAverageAge() {
        return service.calculateAverageAge();
    }
}
