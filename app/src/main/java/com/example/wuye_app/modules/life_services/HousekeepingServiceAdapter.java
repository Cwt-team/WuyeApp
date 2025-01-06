// com/example/wuye_app/modules/life_services/HousekeepingServiceAdapter.java
package com.example.wuye_app.modules.life_services;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wuye_app.R;

import java.util.List;

public class HousekeepingServiceAdapter extends RecyclerView.Adapter<HousekeepingServiceAdapter.ViewHolder> {

    private List<HousekeepingServiceActivity.HousekeepingService> serviceList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public HousekeepingServiceAdapter(List<HousekeepingServiceActivity.HousekeepingService> serviceList) {
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_housekeeping_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HousekeepingServiceActivity.HousekeepingService service = serviceList.get(position);
        holder.serviceNameTextView.setText(service.getName());
        holder.servicePriceTextView.setText(service.getPrice());
        holder.bookButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView serviceNameTextView;
        TextView servicePriceTextView;
        Button bookButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceNameTextView = itemView.findViewById(R.id.serviceName);
            servicePriceTextView = itemView.findViewById(R.id.servicePrice);
            bookButton = itemView.findViewById(R.id.bookButton);
        }
    }
}
