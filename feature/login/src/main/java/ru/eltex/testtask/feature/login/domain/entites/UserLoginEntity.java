package ru.eltex.testtask.feature.login.domain.entites;

public class UserLoginEntity {

    public UserLoginEntity(String name, String password) {
        this.name = name;
        this.password = password;
    }

    private final String name;
    private final String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}