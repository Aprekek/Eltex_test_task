package ru.eltex.testtask.feature.login.domain.repository;

import io.reactivex.rxjava3.core.Single;
import ru.eltex.testtask.feature.login.domain.entites.UserLoginEntity;
import ru.eltex.testtask.shared.user.token.entites.Token;

public interface LoginRepository {

    Single<Token> login(UserLoginEntity user);
}