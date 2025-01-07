package com.example.wuye_app.modules.home.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wuye_app.R;
import com.example.wuye_app.utils.Pair;

import java.util.List;

public class LifeServicesAdapter extends RecyclerView.Adapter<LifeServicesAdapter.ViewHolder> {

    private List<Pair<Integer, String>> lifeServices;
    private OnItemClickListener onItemClickListener;

    public LifeServicesAdapter(List<Pair<Integer, String>> lifeServices) {
        this.lifeServices = lifeServices;
    }

    public void updateData(List<Pair<Integer, String>> newData) {
        this.lifeServices = newData;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_life_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pair<Integer, String> service = lifeServices.get(position);
        holder.icon.setImageResource(service.first);
        holder.name.setText(service.second);

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lifeServices.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.lifeServiceIcon);
            name = itemView.findViewById(R.id.lifeServiceName);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
