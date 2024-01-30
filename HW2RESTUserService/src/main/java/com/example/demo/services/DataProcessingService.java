package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы со списком пользователей
 */
@Service
public class DataProcessingService {

    /**
     * Объект репозитория для хранения пользователей
     */
    @Autowired
    private UserRepository repository;

    /**
     * Добавляет указанного пользователя в список пользователей в репозитории.
     * @param user объект пользователя
     */
    public void addUserToList(User user) {
        repository.getUsers().add(user);
    }

    /**
     * Метод получения списка всех пользователей, зарегистрированных в репозитории.
     * @return список всех пользователей
     */
    public List<User> getAllUsers() {
        return repository.getUsers();
    }

    /**
     * Сортировка списка пользователей по возрасту (по возрастанию).
     * @return список пользователей, отсортированных по возрасту (по возрастанию)
     */
    public List<User> sortUsersByAge() {
        return repository.getUsers().stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    /**
     * Получение пользователей, возраст которых строго больше указанного.
     * @param age возраст пользователя
     * @return список пользователей, возраст которых строго больше указанного
     */
    public List<User> getUsersWithAgeMoreThan(int age) {
        return repository.getUsers().stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    /**
     * Получение пользователей, возраст которых строго меньше указанного.
     * @param age возраст пользователя
     * @return список пользователей, возраст которых строго меньше указанного
     */
    public List<User> getUsersWithAgeLessThan(int age) {
        return repository.getUsers().stream()
                .filter(user -> user.getAge() < age)
                .collect(Collectors.toList());
    }

    /**
     * Получение среднего возраста пользователей.
     * @return средний возраст пользователей или 0, если пользователей нет
     */
    public double calculateAverageAge() {
        return repository.getUsers().stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }
}
