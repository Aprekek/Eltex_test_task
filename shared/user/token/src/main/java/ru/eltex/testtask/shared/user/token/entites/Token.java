package ru.eltex.testtask.shared.user.token.entites;

import java.io.Serializable;

public class Token implements Serializable {

    public Token(String accessToken) {
        this.accessToken = accessToken;
    }

    private final String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
