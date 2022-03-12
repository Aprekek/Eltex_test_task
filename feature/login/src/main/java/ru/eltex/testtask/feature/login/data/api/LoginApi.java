package ru.eltex.testtask.feature.login.data.api;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import ru.eltex.testtask.feature.login.data.model.TokenModel;

public interface LoginApi {

    @FormUrlEncoded
    @POST("oauth/token")
    Single<TokenModel> login(
            @Header("Authorization") String authorization,
            @Field(value = "grant_type") String grantType,
            @Field(value = "password") String password,
            @Field(value = "username") String username
    );
}