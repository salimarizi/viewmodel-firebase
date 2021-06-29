package com.example.modelviewfirebase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.modelviewfirebase.databinding.FragmentStudentFormBinding;

public class StudentFormFragment extends Fragment {
    FragmentStudentFormBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStudentFormBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }
}