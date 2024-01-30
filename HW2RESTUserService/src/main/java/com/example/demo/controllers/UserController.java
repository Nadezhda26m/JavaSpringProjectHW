package com.example.demo.controllers;

import com.example.demo.services.RegistrationService;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-контроллер для работы с сущностями пользователей
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Объект сервиса регистрации пользователей
     */
    @Autowired
    private RegistrationService service;

    /**
     * Метод получения списка всех пользователей.
     * @return список всех пользователей в формате json
     */
    @GetMapping
    public List<User> userList() {
        return service.getDataProcessingService().getAllUsers();
    }

    /**
     * Регистрация нового пользователя, полученного в теле запроса.
     * @param user объект пользователя
     * @return сообщение о добавлении пользователя из тела запроса
     */
    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user) {
        service.processRegistration(user);
        return "User added from body!";
    }

    /**
     * Регистрация нового пользователя с помощью данных, полученных из параметров пути.
     * @param name имя пользователя
     * @param age возраст пользователя
     * @param email электронная почта пользователя
     * @return сообщение о добавлении пользователя из параметров пути
     */
    @PostMapping("/add/{name}/{age}/{email}")
    public String userAddFromParam(@PathVariable("name") String name,
                                   @PathVariable("age") int age,
                                   @PathVariable("email") String email) {
        service.processRegistration(name, age, email);
        return "User added from param!";
    }
}
