package ru.eltex.testtask.feature.login.di;

import androidx.annotation.NonNull;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import retrofit2.Retrofit;
import ru.eltex.testtask.feature.login.data.api.LoginApi;
import ru.eltex.testtask.feature.login.data.datasource.LoginDataSource;
import ru.eltex.testtask.feature.login.data.datasource.LoginDataSourceImpl;
import ru.eltex.testtask.feature.login.data.repository.LoginRepositoryImpl;
import ru.eltex.testtask.feature.login.domain.repository.LoginRepository;

@Module(includes = {LoginModule.LoginBinds.class})
@InstallIn(ViewModelComponent.class)
public class LoginModule {

    @Provides
    LoginApi provideLoginApi(@NonNull Retrofit retrofit) {
        return retrofit.create(LoginApi.class);
    }

    @Module
    @InstallIn(ViewModelComponent.class)
    interface LoginBinds {
        @Binds
        LoginDataSource bindsLoginDataSource(LoginDataSourceImpl impl);

        @Binds
        LoginRepository bindLoginRepository(LoginRepositoryImpl impl);
    }
}