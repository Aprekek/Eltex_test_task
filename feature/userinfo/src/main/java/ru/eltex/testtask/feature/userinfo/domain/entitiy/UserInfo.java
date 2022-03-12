package ru.eltex.testtask.feature.userinfo.domain.entitiy;

import java.util.List;

import javax.inject.Inject;

public class UserInfo {

    @Inject
    public UserInfo(
            String roleId,
            String userName,
            String email,
            List<String> permissions
    ) {
        this.roleId = roleId;
        this.userName = userName;
        this.email = email;
        this.permissions = permissions;
    }

    private final String roleId;
    private final String userName;
    private final String email;
    private final List<String> permissions;

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