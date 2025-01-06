// com/example/wuye_app/modules/setting/SettingsAdapter.java
package com.example.wuye_app.modules.setting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wuye_app.R;

import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {

    private List<SettingsActivity.Pair<Integer, String>> settingOptions;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public SettingsAdapter(List<SettingsActivity.Pair<Integer, String>> settingOptions) {
        this.settingOptions = settingOptions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SettingsActivity.Pair<Integer, String> settingOption = settingOptions.get(position);
        holder.settingIconImageView.setImageResource(settingOption.first);
        holder.settingNameTextView.setText(settingOption.second);
        holder.settingArrowImageView.setVisibility(View.VISIBLE); // 显示右箭头

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return settingOptions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView settingIconImageView;
        TextView settingNameTextView;
        ImageView settingArrowImageView;
        // 可以添加 Switch 或 CheckBox

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            settingIconImageView = itemView.findViewById(R.id.settingIcon);
            settingNameTextView = itemView.findViewById(R.id.settingName);
            settingArrowImageView = itemView.findViewById(R.id.settingArrow);
        }
    }
}
