package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataProcessingService {

    @Autowired
    private UserRepository repository;

    public void addUserToList(User user) {
        repository.getUsers().add(user);
    }

    public List<User> getAllUsers() {
        return repository.getUsers();
    }

    public List<User> sortUsersByAge() {
        return repository.getUsers().stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    public List<User> getUsersWithAgeMoreThan(int age) {
        return repository.getUsers().stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    public List<User> getUsersWithAgeLessThan(int age) {
        return repository.getUsers().stream()
                .filter(user -> user.getAge() < age)
                .collect(Collectors.toList());
    }

    public double calculateAverageAge() {
        return repository.getUsers().stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }
}
