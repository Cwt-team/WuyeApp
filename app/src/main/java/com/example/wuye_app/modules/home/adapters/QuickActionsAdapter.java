// com/example/wuye_app/modules/home/adapters/QuickActionsAdapter.java
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

public class QuickActionsAdapter extends RecyclerView.Adapter<QuickActionsAdapter.ViewHolder> {

    private List<HomeActivity.Pair<Integer, String>> quickActionsList;
    private OnItemClickListener listener;

    public QuickActionsAdapter(List<HomeActivity.Pair<Integer, String>> quickActionsList) {
        this.quickActionsList = quickActionsList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quick_action, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeActivity.Pair<Integer, String> quickAction = quickActionsList.get(position);
        holder.iconImageView.setImageResource(quickAction.first);
        holder.nameTextView.setText(quickAction.second);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return quickActionsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImageView;
        TextView nameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.quickActionIcon);
            nameTextView = itemView.findViewById(R.id.quickActionName);
        }
    }
}
