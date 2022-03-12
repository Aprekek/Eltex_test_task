package ru.eltex.testtask.feature.login.data.mappers;

import ru.eltex.testtask.feature.login.data.model.UserModel;
import ru.eltex.testtask.feature.login.domain.entites.UserLoginEntity;

public class UserMapper {

    public static UserModel entityToModel(UserLoginEntity user) {
        return new UserModel(user.getName(), user.getPassword());
    }
}