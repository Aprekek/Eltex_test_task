package ru.eltex.testtask.feature.userinfo.presentation.states;

import ru.eltex.testtask.feature.userinfo.domain.entitiy.UserInfo;

public abstract class UserInfoState {

    public static class InitialState extends UserInfoState {
    }

    public static class LoadingState extends UserInfoState {
    }

    public static class ContentState extends UserInfoState {

        public ContentState(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        private final UserInfo userInfo;

        public UserInfo getUserInfo() {
            return userInfo;
        }
    }

    public static class ErrorState extends UserInfoState {
    }
}
