// com/example/wuye_app/modules/home/adapters/LifeServicesAdapter.java
package com.example.wuye_app.modules.home.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wuye_app.R;
import com.example.wuye_app.modules.home.HomeActivity;

import java.util.List;

public class LifeServicesAdapter extends RecyclerView.Adapter<LifeServicesAdapter.ViewHolder> {

    private List<HomeActivity.Pair<Integer, String>> lifeServicesList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public LifeServicesAdapter(List<HomeActivity.Pair<Integer, String>> lifeServicesList) {
        this.lifeServicesList = lifeServicesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_life_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeActivity.Pair<Integer, String> lifeService = lifeServicesList.get(position);
        holder.serviceImageView.setImageResource(lifeService.first);
        holder.serviceDescriptionTextView.setText(lifeService.second);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return lifeServicesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView serviceImageView;
        TextView serviceDescriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceImageView = itemView.findViewById(R.id.lifeServiceImage);
            serviceDescriptionTextView = itemView.findViewById(R.id.lifeServiceDescription);
        }
    }
}
