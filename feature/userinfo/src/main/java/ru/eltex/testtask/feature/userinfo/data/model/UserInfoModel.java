package ru.eltex.testtask.feature.userinfo.data.model;

import com.squareup.moshi.Json;

import java.util.List;

public class UserInfoModel {

    String roleId;
    @Json(name = "username")
    String userName;
    String email;
    List<String> permissions;

    public String getRoleId() {
        return roleId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}