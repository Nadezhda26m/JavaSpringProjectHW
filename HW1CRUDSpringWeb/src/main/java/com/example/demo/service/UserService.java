package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с пользователями.
 */
@Service
public class UserService {

    /**
     * Объект репозитория для хранения данных о пользователях.
     */
    private final UserRepository userRepository;

    /**
     * Создание сервиса для работы с пользователями.
     * @param userRepository объект репозитория, хранящего данные о пользователях
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Получение списка всех пользователей из репозитория.
     * @return список пользователей или null, если пользователи не найдены.
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Добавление нового пользователя в репозиторий.
     * @param user объект пользователя с данными имени и фамилии
     * @return объект пользователя с присвоенным ID
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Удаление пользователя из репозитория по его ID.
     * @param id уникальный идентификатор пользователя
     */
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    /**
     * Обновление данных пользователя в репозитории по его ID.
     * @param user объект пользователя с измененными данными имени и фамилии
     */
    public void updateUser(User user) {
        userRepository.update(user);
    }

    /**
     * Получение объекта пользователя из репозитория по его ID.
     * @param id уникальный идентификатор пользователя
     * @return объект пользователя с указанным ID
     */
    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    /**
     * Проверка пользователя на отсутствие null значений.
     * @param user объект пользователя
     * @return false, если есть null
     */
    public boolean isCorrectUser(User user) {
        return user != null
                && user.getFirstName() != null
                && user.getLastName() != null;
    }
}
