// com/example/wuye_app/modules/profile/ProfileOptionsAdapter.java
package com.example.wuye_app.modules.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wuye_app.R;

import java.util.List;

public class ProfileOptionsAdapter extends RecyclerView.Adapter<ProfileOptionsAdapter.ViewHolder> {

    private List<ProfileActivity.Pair<Integer, String>> profileOptionsList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ProfileOptionsAdapter(List<ProfileActivity.Pair<Integer, String>> profileOptionsList) {
        this.profileOptionsList = profileOptionsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_setting, parent, false); // 重用 item_setting.xml
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProfileActivity.Pair<Integer, String> profileOption = profileOptionsList.get(position);
        holder.optionIconImageView.setImageResource(profileOption.first);
        holder.optionNameTextView.setText(profileOption.second);
        holder.rightArrowImageView.setVisibility(View.VISIBLE); // 确保显示右箭头

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return profileOptionsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView optionIconImageView;
        TextView optionNameTextView;
        ImageView rightArrowImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            optionIconImageView = itemView.findViewById(R.id.settingIcon);
            optionNameTextView = itemView.findViewById(R.id.settingName);
            rightArrowImageView = itemView.findViewById(R.id.settingArrow);
        }
    }
}
