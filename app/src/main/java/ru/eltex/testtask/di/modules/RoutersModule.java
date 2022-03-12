package ru.eltex.testtask.di.modules;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import ru.eltex.testtask.feature.login.presentation.LoginRouter;
import ru.eltex.testtask.navigation.routers.LoginRouterImpl;

@Module
@InstallIn(SingletonComponent.class)
public interface RoutersModule {

    @Binds
    LoginRouter bindLoginRouter(LoginRouterImpl impl);
}