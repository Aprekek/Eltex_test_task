package ru.eltex.testtask.di.modules;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ContextModule {

    @Provides
    @Singleton
    public SharedPreferences providesSharedPreferences(@NonNull @ApplicationContext Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }
}
