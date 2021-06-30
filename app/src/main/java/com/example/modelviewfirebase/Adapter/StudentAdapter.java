package com.example.modelviewfirebase.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modelviewfirebase.Entity.Student;
import com.example.modelviewfirebase.R;
import com.example.modelviewfirebase.databinding.StudentDataBinding;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{
    private ArrayList<Student> students;
    private StudentItemListener listener;

    public StudentAdapter(ArrayList<Student> students, StudentItemListener listener) {
        this.students = students;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_data, parent, false);
        return new StudentViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentViewHolder holder, int position) {
        holder.setData(students.get(position));
        holder.itemView.setOnClickListener(v -> {listener.newsDataClicked(students.get(position));});
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public interface StudentItemListener {
        void newsDataClicked(Student student);
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        private StudentDataBinding binding;

        public StudentViewHolder(@NonNull View itemView, final StudentItemListener listener) {
            super(itemView);
            binding = StudentDataBinding.bind(itemView);
        }

        public void setData(Student student){
            binding.idStudent.setText(student.getId());
            binding.nameStudent.setText(student.getFirstName() + " " + student.getLastName());
            binding.addressStudent.setText(student.getAddress());
        }
    }
}
