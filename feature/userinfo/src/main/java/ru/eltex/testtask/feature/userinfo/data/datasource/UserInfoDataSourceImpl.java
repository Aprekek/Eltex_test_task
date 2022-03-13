package ru.eltex.testtask.feature.userinfo.data.datasource;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import ru.eltex.testtask.feature.userinfo.data.api.UserInfoApi;
import ru.eltex.testtask.feature.userinfo.data.model.UserInfoModel;

public class UserInfoDataSourceImpl implements UserInfoDataSource {

    @Inject
    public UserInfoDataSourceImpl(UserInfoApi api) {
        this.api = api;
    }

    private final UserInfoApi api;

    @Override
    public Single<UserInfoModel> getUserInfo(String accessToken) {
        String authorization = "bearer " + accessToken;
        return api.getUserInfo(authorization);
    }
}
