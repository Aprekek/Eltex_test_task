package ru.eltex.testtask.feature.login.presentation;

import ru.eltex.testtask.shared.user.token.entites.Token;

public interface LoginRouter {

    void navigateToUserInfoScreen(Token token);
}