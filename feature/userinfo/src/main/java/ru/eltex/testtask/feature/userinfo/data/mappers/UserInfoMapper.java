package ru.eltex.testtask.feature.userinfo.data.mappers;

import androidx.annotation.NonNull;
import ru.eltex.testtask.feature.userinfo.data.model.UserInfoModel;
import ru.eltex.testtask.feature.userinfo.domain.entitiy.UserInfo;

public class UserInfoMapper {

    @NonNull
    static public UserInfo modelToEntity(@NonNull UserInfoModel userInfoModel) {
        return new UserInfo(
                userInfoModel.getRoleId(),
                userInfoModel.getUserName(),
                userInfoModel.getEmail(),
                userInfoModel.getPermissions()
        );
    }
}
