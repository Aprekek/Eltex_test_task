package ru.eltex.testtask.shared.user.token.entites;

public class Token {

    public Token(String accessToken) {
        this.accessToken = accessToken;
    }

    private final String accessToken;

    public String getAccessToken() {
        return accessToken;
    }
}
