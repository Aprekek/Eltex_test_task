package ru.eltex.testtask.feature.login.data.repository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import ru.eltex.testtask.feature.login.data.datasource.LoginDataSource;
import ru.eltex.testtask.feature.login.data.mappers.TokenMapper;
import ru.eltex.testtask.feature.login.data.mappers.UserMapper;
import ru.eltex.testtask.feature.login.domain.entites.UserLoginEntity;
import ru.eltex.testtask.feature.login.domain.repository.LoginRepository;
import ru.eltex.testtask.shared.user.token.entites.Token;

public class LoginRepositoryImpl implements LoginRepository {

    @Inject
    public LoginRepositoryImpl(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private final LoginDataSource dataSource;

    @Override
    public Single<Token> login(UserLoginEntity user) {
        return dataSource
                .login(UserMapper.entityToModel(user))
                .map(TokenMapper::modelToEntity);
    }
}