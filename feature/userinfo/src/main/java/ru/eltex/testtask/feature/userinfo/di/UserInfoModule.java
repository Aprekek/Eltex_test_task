package ru.eltex.testtask.feature.userinfo.di;

import androidx.annotation.NonNull;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import ru.eltex.testtask.feature.userinfo.data.api.UserInfoApi;
import ru.eltex.testtask.feature.userinfo.data.datasource.UserInfoDataSource;
import ru.eltex.testtask.feature.userinfo.data.datasource.UserInfoDataSourceImpl;
import ru.eltex.testtask.feature.userinfo.data.repository.UserInfoRepositoryImpl;
import ru.eltex.testtask.feature.userinfo.domain.repository.UserInfoRepository;

@Module(includes = UserInfoModule.UserInfoBinds.class)
@InstallIn(ViewModelComponent.class)
public class UserInfoModule {

    @Provides
    UserInfoApi provideUserInfoApi(@NonNull Retrofit retrofit) {
        return retrofit.create(UserInfoApi.class);
    }

    @Module
    @InstallIn(SingletonComponent.class)
    interface UserInfoBinds {
        @Binds
        UserInfoDataSource bindUserInfoDataSource(UserInfoDataSourceImpl impl);

        @Binds
        UserInfoRepository bindUserInfoRepository(UserInfoRepositoryImpl impl);
    }
}
