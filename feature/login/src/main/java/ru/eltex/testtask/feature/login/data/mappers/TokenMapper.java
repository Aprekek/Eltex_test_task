package ru.eltex.testtask.feature.login.data.mappers;

import ru.eltex.testtask.feature.login.data.model.TokenModel;
import ru.eltex.testtask.shared.user.token.entites.Token;

public class TokenMapper {

    public static Token modelToEntity(TokenModel token) {
        return new Token(token.getAccessToken());
    }
}