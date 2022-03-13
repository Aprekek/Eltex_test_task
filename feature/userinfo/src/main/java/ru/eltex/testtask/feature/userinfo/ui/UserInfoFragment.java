package ru.eltex.testtask.feature.userinfo.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import ru.eltex.testtask.feature.userinfo.R;
import ru.eltex.testtask.feature.userinfo.databinding.UserInfoFragmentBinding;
import ru.eltex.testtask.feature.userinfo.domain.entitiy.UserInfo;
import ru.eltex.testtask.feature.userinfo.presentation.UserInfoViewModel;
import ru.eltex.testtask.feature.userinfo.presentation.states.UserInfoState;
import ru.eltex.testtask.feature.userinfo.ui.adapter.PermissionsListAdapter;
import ru.eltex.testtask.shared.user.token.entites.Token;

@AndroidEntryPoint
public class UserInfoFragment extends Fragment {

    private static final String TOKEN_AGR = "TOKEN";

    @NonNull
    public static UserInfoFragment getInstance(Token token) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(TOKEN_AGR, token);

        UserInfoFragment fragment = new UserInfoFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    private UserInfoViewModel viewModel;

    private UserInfoFragmentBinding binding;
    private PermissionsListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(UserInfoViewModel.class);
        if (!viewModel.isWasInitialized())
            viewModel.setData((Token) requireArguments().getSerializable(TOKEN_AGR));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = UserInfoFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initObservers();
        initListeners();
        initAdapter();
    }

    private void initObservers() {
        viewModel.getStateLiveData().observe(
                getViewLifecycleOwner(),
                this::handleState
        );
    }

    private void initListeners() {
        binding.reloadText.setOnClickListener(v -> viewModel.reload());
    }

    private void initAdapter() {
        adapter = new PermissionsListAdapter();
        binding.recyclerView.setAdapter(adapter);
    }

    private void handleState(@NonNull UserInfoState state) {
        if (state instanceof UserInfoState.InitialState
                || state instanceof UserInfoState.LoadingState) showLoading();
        else if (state instanceof UserInfoState.ContentState) showContent((UserInfoState.ContentState) state);
        else if (state instanceof UserInfoState.ErrorState) showError();
    }

    private void showLoading() {
        binding.loadingErrorMessage.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void showContent(@NonNull UserInfoState.ContentState state) {
        binding.progressBar.setVisibility(View.GONE);
        binding.userInfoContent.setVisibility(View.VISIBLE);

        UserInfo userInfo = state.getUserInfo();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();

        adapter.submitList(userInfo.getPermissions());

        binding.roleId.setText(
                appendBold(spannableStringBuilder, getString(R.string.role_id) + " ")
                        .append(userInfo.getRoleId())
        );
        spannableStringBuilder.clear();

        binding.userName.setText(
                appendBold(spannableStringBuilder, getString(R.string.user_name) + " ")
                        .append(userInfo.getUserName())
        );
        spannableStringBuilder.clear();

        String email = userInfo.getEmail();
        binding.email.setText(
                appendBold(spannableStringBuilder, getString(R.string.email) + " ")
                        .append((email != null) ? email : getString(R.string.not_found_sign))
        );
    }

    private void showError() {
        binding.progressBar.setVisibility(View.GONE);
        binding.loadingErrorMessage.setVisibility(View.VISIBLE);
    }

    @NonNull
    private SpannableStringBuilder appendBold(
            @NonNull SpannableStringBuilder builder,
            @NonNull String text
    ) {
        int startIndex = builder.length();
        builder.append(text);
        builder.setSpan(
                new StyleSpan(Typeface.BOLD),
                startIndex,
                builder.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        return builder;
    }

    @Override
    public void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}