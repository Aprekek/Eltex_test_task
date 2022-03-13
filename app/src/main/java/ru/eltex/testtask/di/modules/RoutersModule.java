package ru.eltex.testtask.di.modules;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.components.SingletonComponent;
import ru.eltex.testtask.feature.login.presentation.LoginRouter;
import ru.eltex.testtask.feature.userinfo.presentation.UserInfoRouter;
import ru.eltex.testtask.navigation.routers.LoginRouterImpl;
import ru.eltex.testtask.navigation.routers.UserInfoRouterImpl;

@Module
@InstallIn(ViewModelComponent.class)
public interface RoutersModule {

    @Binds
    LoginRouter bindLoginRouter(LoginRouterImpl impl);

    @Binds
    UserInfoRouter bindUserInfoRouter(UserInfoRouterImpl impl);
}