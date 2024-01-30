package com.example.demo.services;

import com.example.demo.domain.User;
import org.springframework.stereotype.Service;

/**
 * Сервис регистрации пользователей
 */
@Service
public class RegistrationService {

    /**
     * Объект сервиса для работы со списком пользователей
     */
    private DataProcessingService dataProcessingService;

    /**
     * Объект сервиса для создания пользователей
     */
    private UserService userService;

    /**
     * Объект сервиса по отправке сообщений
     */
    private NotificationService notificationService;

    /**
     * Конструктор класса
     * @param dataProcessingService сервис для работы со списком пользователей
     * @param userService сервис для создания пользователей
     * @param notificationService сервис по отправке сообщений
     */
    public RegistrationService(DataProcessingService dataProcessingService,
                               UserService userService,
                               NotificationService notificationService) {
        this.dataProcessingService = dataProcessingService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    /**
     * Метод создает пользователя с указанными данными, добавляет его в сервис для
     * работы со списком пользователей и выводит сообщение о добавлении пользователя.
     * @param name имя пользователя
     * @param age возраст пользователя
     * @param email электронная почта пользователя
     */
    public void processRegistration(String name, int age, String email) {
        User user = userService.createUser(name, age, email);
        dataProcessingService.addUserToList(user);
        notificationService.sendNotification("User added from param!");
    }

    /**
     * Метод добавляет указанного пользователя в сервис для работы со списком
     * пользователей и выводит сообщение о создании и добавлении пользователя.
     * @param user объект пользователя
     */
    public void processRegistration(User user) {
        dataProcessingService.addUserToList(user);
        notificationService.notifyUser(user);
        notificationService.sendNotification("User added from body!");
    }

    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }

}
