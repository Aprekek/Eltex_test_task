package ru.eltex.testtask.shared.preferences.usecases;

import javax.inject.Inject;

import ru.eltex.testtask.shared.preferences.TokenPreferences;
import ru.eltex.testtask.shared.user.token.entites.Token;

public class GetTokenUseCase {

    @Inject
    GetTokenUseCase(TokenPreferences preferences) {
        this.preferences = preferences;
    }

    private final TokenPreferences preferences;

    public Token invoke() {
        return preferences.getToken();
    }
}
