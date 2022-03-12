package ru.eltex.testtask.feature.login.data.datasource;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Single;
import okhttp3.Credentials;
import ru.eltex.testtask.feature.login.data.api.LoginApi;
import ru.eltex.testtask.feature.login.data.model.TokenModel;
import ru.eltex.testtask.feature.login.data.model.UserModel;

public class LoginDataSourceImpl implements LoginDataSource {

    @Inject
    public LoginDataSourceImpl(LoginApi api) {
        this.api = api;
    }

    private final LoginApi api;

    @Override
    public Single<TokenModel> login(@NonNull UserModel user) {
        return api.login(
                Credentials.basic(user.getName(), user.getPassword()),
                user.getGrantType(),
                user.getPassword(),
                user.getName()
        );
    }
}