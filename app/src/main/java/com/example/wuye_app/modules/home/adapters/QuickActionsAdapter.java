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

public class QuickActionsAdapter extends RecyclerView.Adapter<QuickActionsAdapter.ViewHolder> {

    private List<Pair<Integer, String>> quickActions;
    private OnItemClickListener onItemClickListener;

    public QuickActionsAdapter(List<Pair<Integer, String>> quickActions) {
        this.quickActions = quickActions;
    }

    public void updateData(List<Pair<Integer, String>> newData) {
        this.quickActions = newData;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quick_action, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pair<Integer, String> action = quickActions.get(position);
        holder.icon.setImageResource(action.first);
        holder.name.setText(action.second);

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quickActions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.quickActionIcon);
            name = itemView.findViewById(R.id.quickActionName);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
