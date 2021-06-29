package com.example.modelviewfirebase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.modelviewfirebase.databinding.FragmentStudentBinding;

public class StudentFragment extends Fragment {
    private FragmentStudentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStudentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btnAdd.setOnClickListener(view1 -> {
            replaceFragment(new StudentFormFragment());
        });

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}