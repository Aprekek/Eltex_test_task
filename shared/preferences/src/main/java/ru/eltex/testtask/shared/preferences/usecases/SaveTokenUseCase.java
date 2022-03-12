package ru.eltex.testtask.shared.preferences.usecases;

import javax.inject.Inject;

import ru.eltex.testtask.shared.preferences.TokenPreferences;
import ru.eltex.testtask.shared.user.token.entites.Token;

public class SaveTokenUseCase {

    @Inject
    public SaveTokenUseCase(TokenPreferences preferences) {
        this.preferences = preferences;
    }

    private final TokenPreferences preferences;

    public void invoke(Token token) {
        preferences.safeToken(token);
    }
}
