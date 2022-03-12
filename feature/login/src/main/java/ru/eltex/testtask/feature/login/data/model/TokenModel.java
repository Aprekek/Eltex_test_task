package ru.eltex.testtask.feature.login.data.model;

import com.squareup.moshi.Json;

public class TokenModel {

    @Json(name = "access_token")
    String accessToken;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}