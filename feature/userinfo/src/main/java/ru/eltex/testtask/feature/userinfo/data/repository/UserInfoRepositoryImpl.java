package ru.eltex.testtask.feature.userinfo.data.repository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import ru.eltex.testtask.feature.userinfo.data.datasource.UserInfoDataSource;
import ru.eltex.testtask.feature.userinfo.data.mappers.UserInfoMapper;
import ru.eltex.testtask.feature.userinfo.domain.entitiy.UserInfo;
import ru.eltex.testtask.feature.userinfo.domain.repository.UserInfoRepository;

public class UserInfoRepositoryImpl implements UserInfoRepository {

    @Inject
    public UserInfoRepositoryImpl(UserInfoDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private final UserInfoDataSource dataSource;

    @Override
    public Single<UserInfo> getUserInfo(String accessToken) {
        return dataSource.getUserInfo(accessToken).map(UserInfoMapper::modelToEntity);
    }
}
