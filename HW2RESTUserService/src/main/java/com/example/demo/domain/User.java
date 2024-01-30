package com.example.demo.domain;

/**
 * Сущность пользователя
 */
public class User {

    /**
     * Имя пользователя
     */
    private String name;

    /**
     * Возраст пользователя (количество полных лет)
     */
    private int age;

    /**
     * Электронная почта пользователя
     */
    private String email;

    // region setter, getter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // endregion setter, getter
}
