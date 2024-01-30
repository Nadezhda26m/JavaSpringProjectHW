package com.example.demo.services;

import com.example.demo.domain.User;
import org.springframework.stereotype.Service;

/**
 * Сервис по отправке сообщений
 */
@Service
public class NotificationService {

    /**
     * Метод выводит в консоль сообщение о создании нового пользователя и его имя.
     * @param user объект пользователя
     */
    public void notifyUser(User user) {
        System.out.println("A new user has been created: " + user.getName());
    }

    /**
     * Метод выводит в консоль указанное сообщение.
     * @param s строка, которую нужно вывести
     */
    public void sendNotification(String s) {
        System.out.println(s);
    }
}
