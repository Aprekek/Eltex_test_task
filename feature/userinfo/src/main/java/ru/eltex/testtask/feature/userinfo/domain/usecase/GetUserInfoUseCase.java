package ru.eltex.testtask.feature.userinfo.domain.usecase;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import ru.eltex.testtask.feature.userinfo.domain.entitiy.UserInfo;
import ru.eltex.testtask.feature.userinfo.domain.repository.UserInfoRepository;

public class GetUserInfoUseCase {

    @Inject
    public GetUserInfoUseCase(UserInfoRepository repository) {
        this.repository = repository;
    }

    private final UserInfoRepository repository;

    public Single<UserInfo> invoke(String accessToken)
    {
        return repository.getUserInfo(accessToken);
    }
}
