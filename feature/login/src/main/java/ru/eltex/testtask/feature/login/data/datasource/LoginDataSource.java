package ru.eltex.testtask.feature.login.data.datasource;

import io.reactivex.rxjava3.core.Single;
import ru.eltex.testtask.feature.login.data.model.TokenModel;
import ru.eltex.testtask.feature.login.data.model.UserModel;

public interface LoginDataSource {

    Single<TokenModel> login(UserModel user);
}