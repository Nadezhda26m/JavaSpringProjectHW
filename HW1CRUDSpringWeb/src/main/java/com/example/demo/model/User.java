package com.example.demo.model;

import java.util.Objects;

/**
 * Модель пользователя
 */
public class User {

    /**
     * Уникальный идентификатор пользователя
     */
    private int id;

    /**
     * Имя пользователя
     */
    private String firstName;

    /**
     * Фамилия пользователя
     */
    private String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    /**
     * Корректирование данных по шаблону.
     * Установка имени пользователя, если получены корректные данные, или null.
     * @param firstName имя пользователя
     */
    public void setFirstName(String firstName) {
        this.firstName = getCorrectNameOrNull(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * Корректирование данных по шаблону.
     * Установка фамилии пользователя, если получены корректные данные, или null.
     * @param lastName имя пользователя
     */
    public void setLastName(String lastName) {
        this.lastName = getCorrectNameOrNull(lastName);
    }

    /**
     * Корректирование данных. Исключение пробелов в начале и конце строки.
     * Формат строки: первая буква заглавная, остальные строчные.
     * @param name имя/фамилия пользователя
     * @return Корректное имя или null
     */
    private String getCorrectNameOrNull(String name) {
        if (name != null) {
            name = name.trim();
            if (!name.isEmpty()) {
                return name.substring(0, 1).toUpperCase()
                        + name.substring(1).toLowerCase();
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id
                && Objects.equals(firstName, user.getFirstName())
                && Objects.equals(lastName, user.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
