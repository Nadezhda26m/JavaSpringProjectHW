package com.example.demo.model;

import java.util.Objects;

public class User {

    private int id;

    private String firstName;

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

    public void setFirstName(String firstName) {
        this.firstName = getCorrectNameOrNull(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = getCorrectNameOrNull(lastName);
    }

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
