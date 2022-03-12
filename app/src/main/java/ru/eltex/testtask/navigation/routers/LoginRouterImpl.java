package ru.eltex.testtask.navigation.routers;

import com.github.terrakok.cicerone.Router;

import javax.inject.Inject;

import ru.eltex.testtask.feature.login.presentation.LoginRouter;

public class LoginRouterImpl implements LoginRouter {

    @Inject
    public LoginRouterImpl(Router router) {
        this.router = router;
    }

    private final Router router;

    @Override
    public void navigateToUserInfoScreen() {
        // TODO
    }
}