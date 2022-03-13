package ru.eltex.testtask.di.modules;

import com.github.terrakok.cicerone.Cicerone;
import com.github.terrakok.cicerone.NavigatorHolder;
import com.github.terrakok.cicerone.Router;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class NavigationModule {

    @Provides
    @Singleton
    Cicerone<Router> provideCicerone() {
        return Cicerone.create();
    }

    @Provides
    @Singleton
    Router provideRouter(@NonNull Cicerone<Router> cicerone) {
        return cicerone.getRouter();
    }

    @Provides
    @Singleton
    NavigatorHolder provideNavigatorHolder(@NonNull Cicerone<Router> cicerone) {
        return cicerone.getNavigatorHolder();
    }
}