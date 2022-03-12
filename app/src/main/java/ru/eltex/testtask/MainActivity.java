package ru.eltex.testtask;

import android.os.Bundle;

import com.github.terrakok.cicerone.NavigatorHolder;
import com.github.terrakok.cicerone.androidx.AppNavigator;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import dagger.hilt.android.AndroidEntryPoint;
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
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigatorHolder.removeNavigator();
    }
}