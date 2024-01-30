package com.example.demo.services;

import com.example.demo.domain.User;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private DataProcessingService dataProcessingService;
    private UserService userService;
    private NotificationService notificationService;

    public RegistrationService(DataProcessingService dataProcessingService,
                               UserService userService,
                               NotificationService notificationService) {
        this.dataProcessingService = dataProcessingService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }

    public void processRegistration(String name, int age, String email) {
        User user = userService.createUser(name, age, email);
        dataProcessingService.addUserToList(user);
        notificationService.sendNotification("User added from param!");
    }

    public void processRegistration(User user) {
        dataProcessingService.addUserToList(user);
        notificationService.notifyUser(user);
        notificationService.sendNotification("User added from body!");
    }

}
