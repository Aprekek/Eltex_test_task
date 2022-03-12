package ru.eltex.testtask;

import android.os.Bundle;

import com.github.terrakok.cicerone.Command;
import com.github.terrakok.cicerone.NavigatorHolder;
import com.github.terrakok.cicerone.Replace;
import com.github.terrakok.cicerone.androidx.AppNavigator;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;
import ru.eltex.testtask.feature.login.LoginScreen;
import ru.eltex.testtask.shared.preferences.usecases.GetTokenUseCase;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

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
        if (getTokenUseCase.invoke().getAccessToken() == null) {
            navigator.applyCommands(
                    new Command[]{new Replace(LoginScreen.get())}
            );
        } else {
            // TODO navigate to the userinfo
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigatorHolder.removeNavigator();
    }
}