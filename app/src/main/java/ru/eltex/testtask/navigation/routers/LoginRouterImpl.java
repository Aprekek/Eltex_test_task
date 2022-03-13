package ru.eltex.testtask.navigation.routers;

import com.github.terrakok.cicerone.Router;

import javax.inject.Inject;

import ru.eltex.testtask.feature.login.presentation.LoginRouter;
import ru.eltex.testtask.feature.userinfo.UserInfoScreen;
import ru.eltex.testtask.shared.user.token.entites.Token;

public class LoginRouterImpl implements LoginRouter {

    @Inject
    public LoginRouterImpl(Router router) {
        this.router = router;
    }

    private final Router router;

    @Override
    public void navigateToUserInfoScreen(Token token) {
        router.newRootScreen(UserInfoScreen.get(token));
    }
}