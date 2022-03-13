package ru.eltex.testtask.feature.login.presentation;

import android.util.Log;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import ru.eltex.testtask.feature.login.domain.entites.UserLoginEntity;
import ru.eltex.testtask.feature.login.domain.usecases.LoginUserUseCase;
import ru.eltex.testtask.shared.preferences.usecases.SaveTokenUseCase;
import ru.eltex.testtask.shared.user.token.entites.Token;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    @Inject
    public LoginViewModel(
            LoginRouter router,
            LoginUserUseCase loginUserUseCase,
            SaveTokenUseCase saveTokenUseCase
    ) {
        this.router = router;
        this.loginUserUseCase = loginUserUseCase;
        this.saveTokenUseCase = saveTokenUseCase;
    }

    private Disposable disposable;

    private final LoginRouter router;

    private final LoginUserUseCase loginUserUseCase;
    private final SaveTokenUseCase saveTokenUseCase;

    private final MutableLiveData<String> userNameLiveData = new MutableLiveData<String>("");
    private final MutableLiveData<String> passwordLiveData = new MutableLiveData<String>("");

    private final MutableLiveData<Boolean> userNameError = new MutableLiveData<Boolean>(false);
    private final MutableLiveData<Boolean> passwordError = new MutableLiveData<Boolean>(false);
    private final MutableLiveData<Boolean> loginError = new MutableLiveData<Boolean>(false);

    public void onLoginAction() {
        if (validateAll()) {
            getAccessTokenAndNavigate();
        }
    }

    public void loginErrorWasShown() {
        loginError.setValue(false);
    }

    private Boolean validateAll() {
        boolean allValid = true;

        if (userNameLiveData.getValue().isEmpty()) {
            userNameError.setValue(true);
            allValid = false;
        }
        if (passwordLiveData.getValue().isEmpty()) {
            passwordError.setValue(true);
            allValid = false;
        }

        return allValid;
    }

    private void getAccessTokenAndNavigate() {
        disposable = loginUserUseCase
                .invoke(new UserLoginEntity(userNameLiveData.getValue(), passwordLiveData.getValue()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        token -> {
                            saveTokenUseCase.invoke(token);
                            navigateToUserInfoScreen(token);
                        },
                        throwable -> {
                            Log.d(this.getClass().getName(), throwable.getMessage());
                            loginError.setValue(true);
                        }
                );
    }

    private void navigateToUserInfoScreen(Token token) {
        router.navigateToUserInfoScreen(token);
    }

    public void onUserNameTextChanged(String useName) {
        userNameLiveData.setValue(useName);
    }

    public void onPasswordChanged(String password) {
        passwordLiveData.setValue(password);
    }

    public LiveData<String> getUserNameLiveData() {
        return userNameLiveData;
    }

    public LiveData<String> getPasswordLiveData() {
        return passwordLiveData;
    }

    public LiveData<Boolean> getUserNameError() {
        return userNameError;
    }

    public LiveData<Boolean> getPasswordError() {
        return passwordError;
    }

    public LiveData<Boolean> getLoginError() {
        return loginError;
    }

    @Override
    protected void onCleared() {
        if (disposable != null)
            disposable.dispose();
        super.onCleared();
    }
}