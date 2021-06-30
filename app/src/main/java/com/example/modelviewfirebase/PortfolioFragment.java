package com.example.modelviewfirebase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.modelviewfirebase.Adapter.PortofolioAdapter;
import com.example.modelviewfirebase.Adapter.StudentAdapter;
import com.example.modelviewfirebase.Entity.Portfolio;
import com.example.modelviewfirebase.Entity.Student;
import com.example.modelviewfirebase.databinding.FragmentPortfolioBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PortfolioFragment extends Fragment {
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase2;
    private ArrayList<Student> students;
    private ArrayList<Portfolio> portfolios;
    private StudentAdapter studentAdapter;
    private PortofolioAdapter portofolioAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FragmentPortfolioBinding binding;
    private ArrayAdapter<String> arrayAdapter;
    private String student,title,description;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference("students");
        students = new ArrayList<Student>();
        fetchData();

        mDatabase2 = FirebaseDatabase.getInstance().getReference("portfolios");
        portfolios = new ArrayList<Portfolio>();
        fetchData2();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPortfolioBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        studentAdapter = new StudentAdapter(students,student -> {});
        portofolioAdapter = new PortofolioAdapter(portfolios,portfolio -> {});
        arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, students);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerStudent.setAdapter(arrayAdapter);

        binding.spinnerStudent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String student2 = (String) parent.getItemAtPosition(position);
                Toast.makeText(parent.getContext(), "Selected: " + student2,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        binding.rvPorto.setAdapter(portofolioAdapter);
        binding.rvPorto.setLayoutManager(layoutManager);

        mDatabase2 = FirebaseDatabase.getInstance().getReference("portfolios");
        binding.btSave.setOnClickListener(view1 -> {
            student = binding.spinnerStudent.getSelectedItem().toString();
            title = binding.editTitle.getText().toString();
            description = binding.editDesc.getText().toString();
            writeNewPorto(student,title,description);
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

    public void fetchData2(){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    Portfolio portfolio = new Portfolio();
                    portfolio.setStudent(ds.child("student").getValue(String.class));
                    portfolio.setTitle(ds.child("title").getValue(String.class));
                    portfolio.setDescription(ds.child("description").getValue(String.class));

                    portfolios.add(portfolio);
                }
                studentAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void writeNewPorto(String student, String title, String description) {
        Portfolio portfolio = new Portfolio(student, title, description);
        mDatabase2.push().setValue(portfolio);
    }
}