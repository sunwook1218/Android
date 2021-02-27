package com.example.viewbinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.viewbinding.databinding.FragTestBinding;

public class TestFragment extends Fragment {

    private FragTestBinding fragTestBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragTestBinding = FragTestBinding.inflate(inflater, container, false);

        fragTestBinding.btnFragment.setOnClickListener((v) -> {
            fragTestBinding.tvFragment.setText("버튼이 클릭되었습니다");
        });

        return fragTestBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragTestBinding = null;
    }
}
