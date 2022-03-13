package ru.eltex.testtask.feature.userinfo.domain.repository;

import io.reactivex.rxjava3.core.Single;
import ru.eltex.testtask.feature.userinfo.domain.entitiy.UserInfo;

public interface UserInfoRepository {

    Single<UserInfo> getUserInfo(String accessToken);
}