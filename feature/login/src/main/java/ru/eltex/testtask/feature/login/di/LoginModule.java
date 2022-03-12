package ru.eltex.testtask.feature.login.di;

import androidx.annotation.NonNull;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import ru.eltex.testtask.feature.login.data.api.LoginApi;
import ru.eltex.testtask.feature.login.data.datasource.LoginDataSource;
import ru.eltex.testtask.feature.login.data.datasource.LoginDataSourceImpl;
import ru.eltex.testtask.feature.login.data.repository.LoginRepositoryImpl;
import ru.eltex.testtask.feature.login.domain.repository.LoginRepository;
import ru.eltex.testtask.feature.login.domain.usecases.LoginUserUseCase;
import ru.eltex.testtask.network.di.NetworkModule;

@Module(includes = {LoginModule.LoginBinds.class, NetworkModule.class})
@InstallIn(SingletonComponent.class)
public class LoginModule {

    @Provides
    LoginApi provideLoginApi(@NonNull Retrofit retrofit) {
        return retrofit.create(LoginApi.class);
    }

    @Provides
    LoginDataSourceImpl provideLoginDataSource(LoginApi api) {
        return new LoginDataSourceImpl(api);
    }

    @Provides
    LoginRepositoryImpl providesLoginRepository(LoginDataSource dataSource) {
        return new LoginRepositoryImpl(dataSource);
    }

    @Module
    @InstallIn(SingletonComponent.class)
    interface LoginBinds {
        @Binds
        LoginDataSource bindsLoginDataSource(LoginDataSourceImpl impl);

        @Binds
        LoginRepository bindLoginRepository(LoginRepositoryImpl impl);
    }
}