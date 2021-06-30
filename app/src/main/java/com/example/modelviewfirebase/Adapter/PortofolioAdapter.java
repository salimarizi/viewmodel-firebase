package com.example.modelviewfirebase.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modelviewfirebase.Entity.Portfolio;
import com.example.modelviewfirebase.R;
import com.example.modelviewfirebase.databinding.PortfolioDataBinding;

import java.util.ArrayList;

public class PortofolioAdapter extends RecyclerView.Adapter<PortofolioAdapter.PortofolioViewHolder>{
    private ArrayList<Portfolio> portofolios;
    private PortofolioItemListener listener;

    public PortofolioAdapter(ArrayList<Portfolio> portofolios, PortofolioItemListener listener) {
            this.portofolios = portofolios;
            this.listener = listener;
            }

    @NonNull
    @Override
    public PortofolioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.portfolio_data, parent, false);
        return new PortofolioAdapter.PortofolioViewHolder(view, (StudentAdapter.StudentItemListener) listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PortofolioAdapter.PortofolioViewHolder holder, int position) {
        holder.setData(portofolios.get(position));
        holder.itemView.setOnClickListener(v -> {listener.newsDataClicked(portofolios.get(position));});
    }

    @Override
    public int getItemCount() {
        return portofolios.size();
    }

    public interface PortofolioItemListener {
        void newsDataClicked(Portfolio portfolio);
    }

    static class PortofolioViewHolder extends RecyclerView.ViewHolder {
        private PortfolioDataBinding binding;

        public PortofolioViewHolder(@NonNull View itemView, final StudentAdapter.StudentItemListener listener) {
            super(itemView);
            binding = PortfolioDataBinding.bind(itemView);
        }

        public void setData(Portfolio portofolio){
            binding.studentName.setText(portofolio.getStudent());
            binding.portoTitle.setText(portofolio.getTitle());
            binding.portoDesc.setText(portofolio.getDescription());
        }
    }
}
