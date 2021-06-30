package com.example.modelviewfirebase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.modelviewfirebase.Adapter.StudentAdapter;
import com.example.modelviewfirebase.Entity.Student;
import com.example.modelviewfirebase.databinding.FragmentStudentBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentFragment extends Fragment {
    private FragmentStudentBinding binding;
    private DatabaseReference mDatabase;
    private ArrayList<Student> students;
    private StudentAdapter studentAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference("students");
        students = new ArrayList<Student>();
        fetchData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStudentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        layoutManager = new LinearLayoutManager(getContext());
        studentAdapter = new StudentAdapter(students,student -> {});
        binding.rvStudent.setLayoutManager(layoutManager);
        binding.rvStudent.setAdapter(studentAdapter);

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

    public void fetchData(){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    Student student = new Student();
                    student.setId(ds.child("id").getValue(String.class));
                    student.setFirstName(ds.child("firstName").getValue(String.class));
                    student.setLastName(ds.child("lastName").getValue(String.class));
                    student.setAddress(ds.child("address").getValue(String.class));

                    students.add(student);
                }
                studentAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}