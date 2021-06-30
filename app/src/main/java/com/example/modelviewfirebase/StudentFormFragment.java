package com.example.modelviewfirebase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.modelviewfirebase.Entity.Student;
import com.example.modelviewfirebase.databinding.FragmentStudentFormBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Observable;

public class StudentFormFragment extends Fragment {
    FragmentStudentFormBinding binding;
    private DatabaseReference mDatabase;
    String id, firstName, lastName, address;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentStudentFormBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        mDatabase = FirebaseDatabase.getInstance().getReference("students");

        binding.btnSave.setOnClickListener(view1 -> {
            id = binding.txtID.getText().toString();
            firstName = binding.txtFirstName.getText().toString();
            lastName = binding.txtLastName.getText().toString();
            address = binding.txtAddress.getText().toString();
            writeNewStudent(id,firstName,lastName,address);
        });

        return view;
    }

    public void writeNewStudent(String id, String firstName, String lastName, String address) {
        Student student = new Student(id, firstName, lastName, address);
        mDatabase.push().setValue(student);
    }

}