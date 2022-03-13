package ru.eltex.testtask.feature.userinfo;

import com.github.terrakok.cicerone.androidx.FragmentScreen;

import androidx.annotation.NonNull;
import ru.eltex.testtask.feature.userinfo.ui.UserInfoFragment;
import ru.eltex.testtask.shared.user.token.entites.Token;

public class UserInfoScreen {

    @NonNull
    static public FragmentScreen get(Token token) {
        return FragmentScreen.Companion.invoke(
                null,
                true,
                fragmentFactory -> UserInfoFragment.getInstance(token)
        );
    }
}