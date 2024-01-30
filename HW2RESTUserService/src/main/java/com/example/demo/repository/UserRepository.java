package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для хранения пользователей
 */
@Component
public class UserRepository {

    /**
     * Список пользователей
     */
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
