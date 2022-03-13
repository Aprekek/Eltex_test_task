package ru.eltex.testtask.feature.login;

import com.github.terrakok.cicerone.androidx.FragmentScreen;

import androidx.annotation.NonNull;
import ru.eltex.testtask.feature.login.ui.LoginFragment;

public class LoginScreen {

    @NonNull
    static public FragmentScreen get() {
        return FragmentScreen.Companion.invoke(
                null,
                true,
                fragmentFactory -> LoginFragment.getInstance()
        );
    }
}