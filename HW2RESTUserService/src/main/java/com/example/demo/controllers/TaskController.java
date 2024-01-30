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

/**
 * REST-контроллер для работы со списком пользователей
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    /**
     * Объект сервиса для работы со списком пользователей
     */
    @Autowired
    private DataProcessingService service;

    /**
     * Получение списка задач, доступных в данном контроллере.
     * @return список доступных задач
     */
    @GetMapping
    public List<String> getAllTasks() {
        List<String> tasks = new ArrayList<>();
        tasks.add("ageSort");
        tasks.add("filter/more/{age}");
        tasks.add("filter/less/{age}");
        tasks.add("calc");
        return tasks;
    }

    /**
     * Метод получения списка пользователей, отсортированных по возрасту.
     * @return список пользователей, отсортированных по возрасту (по возрастанию)
     */
    @GetMapping("/ageSort")
    public List<User> sortUsersByAge() {
        return service.sortUsersByAge();
    }

    /**
     * Метод получения списка пользователей, возраст которых строго больше указанного.
     * @param age возраст пользователя
     * @return список пользователей, возраст которых строго больше указанного
     */
    @GetMapping("/filter/more/{age}")
    public List<User> filterUsersByAgeWhichMore(@PathVariable("age") int age) {
        return service.getUsersWithAgeMoreThan(age);
    }

    /**
     * Метод получения списка пользователей, возраст которых строго меньше указанного.
     * @param age возраст пользователя
     * @return список пользователей, возраст которых строго меньше указанного
     */
    @GetMapping("/filter/less/{age}")
    public List<User> filterUsersByAgeWhichLess(@PathVariable("age") int age) {
        return service.getUsersWithAgeLessThan(age);
    }

    /**
     * Метод получения среднего возраста пользователей.
     * @return средний возраст пользователей или 0, если пользователей нет
     */
    @GetMapping("/calc")
    public double calculateAverageAge() {
        return service.calculateAverageAge();
    }
}
