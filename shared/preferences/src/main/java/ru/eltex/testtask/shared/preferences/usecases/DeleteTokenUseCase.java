package ru.eltex.testtask.shared.preferences.usecases;

import javax.inject.Inject;

import ru.eltex.testtask.shared.preferences.TokenPreferences;

public class DeleteTokenUseCase {

    @Inject
    DeleteTokenUseCase(TokenPreferences preferences) {
        this.preferences = preferences;
    }

    private final TokenPreferences preferences;

    public void invoke() {
        preferences.deleteToken();
    }
}