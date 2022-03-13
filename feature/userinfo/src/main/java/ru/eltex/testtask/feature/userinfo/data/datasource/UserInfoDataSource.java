package ru.eltex.testtask.feature.userinfo.data.datasource;

import io.reactivex.rxjava3.core.Single;
import ru.eltex.testtask.feature.userinfo.data.model.UserInfoModel;

public interface UserInfoDataSource {

    Single<UserInfoModel> getUserInfo(String accessToken);
}
