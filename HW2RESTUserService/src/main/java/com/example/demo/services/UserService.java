package com.example.demo.services;

import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис для создания пользователей
 */
@Service
public class UserService {

    /**
     * Объект сервиса по отправке сообщений
     */
    @Autowired
    private NotificationService notificationService;

    /**
     * Метод создает нового пользователя с указанными данными и выводит в консоль
     * сообщение о создании нового пользователя и его имя.
     * @param name имя пользователя
     * @param age возраст пользователя
     * @param email электронная почта пользователя
     * @return объект пользователя с указанными данными
     */
    public User createUser(String name, int age, String email) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);

        notificationService.notifyUser(user);

        return user;
    }
}
