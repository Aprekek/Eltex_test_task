package ru.eltex.testtask.feature.userinfo.presentation;

import android.util.Log;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.HttpException;
import ru.eltex.testtask.feature.userinfo.domain.usecase.GetUserInfoUseCase;
import ru.eltex.testtask.feature.userinfo.presentation.states.UserInfoState;
import ru.eltex.testtask.shared.preferences.usecases.DeleteTokenUseCase;
import ru.eltex.testtask.shared.user.token.entites.Token;

@HiltViewModel
public class UserInfoViewModel extends ViewModel {

    @Inject
    public UserInfoViewModel(
            UserInfoRouter router,
            DeleteTokenUseCase deleteTokenUseCase,
            GetUserInfoUseCase getUserInfoUseCase
    ) {
        this.router = router;
        this.deleteTokenUseCase = deleteTokenUseCase;
        this.getUserInfoUseCase = getUserInfoUseCase;
    }

    private static final int AUTHORIZATION_ERROR_CORE = 401;

    private boolean isWasInitialized = false;
    private Disposable disposable = null;

    private final UserInfoRouter router;

    private final GetUserInfoUseCase getUserInfoUseCase;
    private final DeleteTokenUseCase deleteTokenUseCase;

    private final MutableLiveData<UserInfoState> state = new MutableLiveData<UserInfoState>(new UserInfoState.InitialState());
    private Token token;

    public void setData(Token token) {
        this.token = token;
        isWasInitialized = true;

        loadUserInfo();
    }

    public void reload() {
        loadUserInfo();
    }

    private void loadUserInfo() {
        state.setValue(new UserInfoState.LoadingState());
        disposable = getUserInfoUseCase.invoke(token.getAccessToken())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userInfo -> state.setValue(new UserInfoState.ContentState(userInfo)),
                        this::onLoadingError
                );
    }

    private void onLoadingError(@NonNull Throwable throwable) {
        Log.d(this.getClass().getName(), throwable.getMessage());

        if (throwable instanceof HttpException) {
            if (((HttpException) throwable).code() == AUTHORIZATION_ERROR_CORE) {
                deleteTokenUseCase.invoke();
                router.navigateToLoginScreen();
            } else {
                setErrorState();
            }
        } else {
            setErrorState();
        }
    }

    private void setErrorState() {
        state.setValue(new UserInfoState.ErrorState());
    }

    public LiveData<UserInfoState> getStateLiveData() {
        return state;
    }

    public boolean isWasInitialized() {
        return isWasInitialized;
    }

    @Override
    protected void onCleared() {
        if (disposable != null)
            disposable.dispose();

        super.onCleared();
    }
}