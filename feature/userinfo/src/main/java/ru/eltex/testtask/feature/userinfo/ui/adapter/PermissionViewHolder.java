package ru.eltex.testtask.feature.userinfo.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ru.eltex.testtask.feature.userinfo.databinding.PermissionItemBinding;

public class PermissionViewHolder extends RecyclerView.ViewHolder {

    private PermissionViewHolder(@NonNull PermissionItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    private final PermissionItemBinding binding;

    @NonNull
    public static PermissionViewHolder from(@NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PermissionItemBinding binding = PermissionItemBinding.inflate(inflater, parent, false);

        return new PermissionViewHolder(binding);
    }

    public void bind(String permission) {
        binding.permission.setText(permission);
    }
}