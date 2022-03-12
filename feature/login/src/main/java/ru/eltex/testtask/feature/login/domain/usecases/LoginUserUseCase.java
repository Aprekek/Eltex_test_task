package ru.eltex.testtask.feature.login.domain.usecases;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import ru.eltex.testtask.feature.login.domain.entites.UserLoginEntity;
import ru.eltex.testtask.feature.login.domain.repository.LoginRepository;
import ru.eltex.testtask.shared.user.token.entites.Token;

public class LoginUserUseCase {

    @Inject
    public LoginUserUseCase(LoginRepository repository) {
        this.repository = repository;
    }

    private final LoginRepository repository;

    public Single<Token> invoke(UserLoginEntity user) {
        return repository.login(user);
    }
}