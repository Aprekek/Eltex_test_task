package ru.eltex.testtask.feature.login.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import ru.eltex.testtask.feature.login.R;
import ru.eltex.testtask.feature.login.databinding.LoginFragmentBinding;
import ru.eltex.testtask.feature.login.presentation.LoginViewModel;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    @NonNull
    static public LoginFragment getInstance() {
        return new LoginFragment();
    }

    private LoginFragmentBinding binding;

    private LoginViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = LoginFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setObservers();
        setListeners();
    }

    private void setListeners() {
        doAfterTextChanged(binding.usernameEditText, viewModel::onUserNameTextChanged);
        doAfterTextChanged(binding.passwordEditText, viewModel::onPasswordChanged);

        binding.loginButton.setOnClickListener(view -> viewModel.onLoginAction());
    }

    private void setObservers() {
        viewModel.getUserNameLiveData().observe(
                getViewLifecycleOwner(),
                userName -> compareAndSetText(binding.usernameEditText, userName)
        );
        viewModel.getPasswordLiveData().observe(
                getViewLifecycleOwner(),
                password -> compareAndSetText(binding.passwordEditText, password)
        );

        viewModel.getLoginError().observe(
                getViewLifecycleOwner(),
                isError -> {
                    if (isError) showLoginError();
                }
        );
        viewModel.getUserNameError().observe(
                getViewLifecycleOwner(),
                isError -> compareAndSetError(
                        binding.usernameInputLayout,
                        getResources().getString(R.string.empty_field_error),
                        isError
                )
        );
        viewModel.getPasswordError().observe(
                getViewLifecycleOwner(),
                isError -> compareAndSetError(
                        binding.passwordInputLayout,
                        getResources().getString(R.string.empty_field_error),
                        isError
                )
        );
    }

    private void showLoginError() {
        Toast.makeText(
                requireContext(),
                getResources().getString(R.string.login_error),
                Toast.LENGTH_LONG).show();

        viewModel.loginErrorWasShown();
    }

    private void compareAndSetError(
            @NonNull TextInputLayout textInputLayout,
            String errorMessage,
            boolean isError
    ) {
        if (isError) {
            textInputLayout.setError(errorMessage);
        } else {
            textInputLayout.setError(null);
        }
    }

    private void compareAndSetText(@NonNull EditText editText, String newText) {
        if (!editText.getText().toString().equals(newText))
            editText.setText(newText);
    }

    private void doAfterTextChanged(
            @NonNull EditText editText,
            @NonNull OnTextChangedAction<String> afterTextChanged
    ) {
        editText.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        afterTextChanged.onTextChanged(s.toString());
                    }
                }
        );
    }

    @FunctionalInterface
    public interface OnTextChangedAction<String> {

        void onTextChanged(String s);
    }

    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}