package ru.eltex.testtask;

import android.os.Bundle;

import com.github.terrakok.cicerone.NavigatorHolder;
import com.github.terrakok.cicerone.Router;
import com.github.terrakok.cicerone.androidx.AppNavigator;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;
import ru.eltex.testtask.feature.login.LoginScreen;
import ru.eltex.testtask.feature.userinfo.UserInfoScreen;
import ru.eltex.testtask.shared.preferences.usecases.GetTokenUseCase;
import ru.eltex.testtask.shared.user.token.entites.Token;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Inject
    Router router;
    @Inject
    NavigatorHolder navigatorHolder;
    private final AppNavigator navigator = new AppNavigator(this, R.id.container);

    @Inject
    GetTokenUseCase getTokenUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigatorHolder.setNavigator(navigator);
        setStartingScreen();
    }

    private void setStartingScreen() {
        Token token = getTokenUseCase.invoke();

        if (token.getAccessToken() == null) {
            router.newRootScreen(LoginScreen.get());
        } else {
            router.newRootScreen(UserInfoScreen.get(token));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigatorHolder.removeNavigator();
    }
}