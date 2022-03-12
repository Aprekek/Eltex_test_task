package ru.eltex.testtask.feature.login.data.model;

public class UserModel {

    public UserModel(String name, String password) {
        this.name = name;
        this.password = password;
    }

    private static final String GRANT_TYPE = "password";

    private final String name;
    private final String password;

    public String getGrantType() {
        return GRANT_TYPE;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}