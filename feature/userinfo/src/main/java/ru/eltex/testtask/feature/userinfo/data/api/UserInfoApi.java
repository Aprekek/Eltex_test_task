package ru.eltex.testtask.feature.userinfo.data.api;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import ru.eltex.testtask.feature.userinfo.data.model.UserInfoModel;

public interface UserInfoApi {

    @GET(value = "user")
    Single<UserInfoModel> getUserInfo(
            @Header("Authorization") String authorization
    );
}
