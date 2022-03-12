package ru.eltex.testtask.shared.preferences;

import android.content.SharedPreferences;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import ru.eltex.testtask.shared.user.token.entites.Token;

public class TokenPreferences {

    @Inject
    public TokenPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    private static final String TOKEN_KEY = "TOKEN";

    private final SharedPreferences preferences;

    public Token getToken() {
        return new Token(preferences.getString(TOKEN_KEY, null));
    }

    public void safeToken(@NonNull Token token) {
        preferences.edit().putString(TOKEN_KEY, token.getAccessToken()).apply();
    }
}
