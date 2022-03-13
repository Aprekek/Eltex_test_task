package ru.eltex.testtask.navigation.routers;

import com.github.terrakok.cicerone.Router;

import javax.inject.Inject;

import ru.eltex.testtask.feature.login.LoginScreen;
import ru.eltex.testtask.feature.userinfo.presentation.UserInfoRouter;

public class UserInfoRouterImpl implements UserInfoRouter {

    @Inject
    public UserInfoRouterImpl(Router router) {
        this.router = router;
    }

    private final Router router;

    @Override
    public void navigateToLoginScreen() {
        router.newRootScreen(LoginScreen.get());
    }
}