// com/example/wuye_app/modules/home/HomeActivity.java
package com.example.wuye_app.modules.home;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.wuye_app.utils.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.wuye_app.R; // 确保导入了你的 R 文件
import com.example.wuye_app.data.model.HomeDataResponse;
import com.example.wuye_app.data.model.LifeService;
import com.example.wuye_app.data.model.QuickAction;
import com.example.wuye_app.data.remote.ApiService;
import com.example.wuye_app.data.remote.RetrofitClient;
import com.example.wuye_app.modules.notification.NotificationListActivity;
import com.example.wuye_app.modules.profile.ProfileActivity;
import com.example.wuye_app.modules.home.adapters.LifeServicesAdapter;
import com.example.wuye_app.modules.home.adapters.QuickActionsAdapter;
import com.example.wuye_app.utils.NetworkUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private HomeViewModel homeViewModel;
    private QuickActionsAdapter quickActionsAdapter;
    private LifeServicesAdapter lifeServicesAdapter;

    public static Intent newIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    private BottomNavigationView bottomNavigationView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView userNameTextView;
    private TextView communityNameTextView;
    private View loadingIndicator;
    private TextView errorView;
    private ViewPager2 carouselViewPager;
    private RecyclerView quickActionsRecyclerView;
    private RecyclerView lifeServicesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // Initialize UI elements
        carouselViewPager = findViewById(R.id.imageCarousel); // Use the correct ID from your XML
        userNameTextView = findViewById(R.id.userNameTextView);
        communityNameTextView = findViewById(R.id.userAddressTextView); // Assuming userAddressTextView is intended for community name
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        quickActionsRecyclerView = findViewById(R.id.quickActionsRecyclerView);
        lifeServicesRecyclerView = findViewById(R.id.lifeServicesRecyclerView);
        loadingIndicator = findViewById(R.id.noNotificationLayout);
        errorView = findViewById(R.id.noNotificationDetailsTextView);

        // Set up BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                return true;
            } else if (itemId == R.id.navigation_notifications) {
                startActivity(new Intent(this, NotificationListActivity.class));
                return true;
            } else if (itemId == R.id.navigation_door) {
                Toast.makeText(this, "Clicked 开锁", Toast.LENGTH_SHORT).show();
                // 处理开锁逻辑
                return true;
            } else if (itemId == R.id.navigation_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                return true;
            }
            return false;
        });

        // Set up SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this::loadData);

        // Set up Quick Actions GridLayout
        quickActionsAdapter = new QuickActionsAdapter(new ArrayList<>());
        setupQuickActionButtons();

        // Set up Life Services HorizontalScrollView
        lifeServicesAdapter = new LifeServicesAdapter(new ArrayList<>());
        setupLifeServiceCards();

        // Observe ViewModel data (Example - adjust as needed)
        homeViewModel.getCommunityName().observe(this, communityName -> {
            // userNameTextView.setText(communityName); // Example usage
        });

        // Set click listeners for Quick Actions
        quickActionsAdapter.setOnItemClickListener(position -> {
            if (position >= 0 && position < quickActionsAdapter.getItemCount()) {
                Pair<Integer, String> action = quickActionsAdapter.getItem(position);
                if (action != null) {
                    String actionName = action.second;
                    Toast.makeText(this, "Clicked quick action: " + actionName, Toast.LENGTH_SHORT).show();
                    // Handle actions based on actionName
                }
            }
        });

        // Set click listeners for Life Services
        lifeServicesAdapter.setOnItemClickListener(position -> {
            if (position >= 0 && position < lifeServicesAdapter.getItemCount()) {
                Pair<Integer, String> service = lifeServicesAdapter.getItem(position);
                if (service != null) {
                    String serviceName = service.second;
                    Toast.makeText(this, "Clicked life service: " + serviceName, Toast.LENGTH_SHORT).show();
                    // Handle actions based on serviceName
                }
            }
        });

        // Set up carousel
        List<Integer> carouselImages = new ArrayList<>();
        carouselImages.add(R.drawable.home2_1);
        carouselImages.add(R.drawable.home2_2);

        CarouselAdapter carouselAdapter = new CarouselAdapter(carouselImages);
        carouselViewPager.setAdapter(carouselAdapter);
    }

    private class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.ViewHolder> {
        private List<Integer> images;

        CarouselAdapter(List<Integer> images) {
            this.images = images;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_carousel_image, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.imageView.setImageResource(images.get(position));
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            ViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.carouselImage); // Assuming you have an ImageView with this ID in item_carousel_image.xml
            }
        }
    }

    private void loadData() {
        if (!NetworkUtils.getInstance(this).isNetworkAvailable()) {
            showNetworkError();
            return;
        }

        swipeRefreshLayout.setRefreshing(true);
        showLoadingState();
        errorView.setVisibility(View.GONE);

        ApiService apiService = RetrofitClient.getInstance().getApiService();
        Call<HomeDataResponse> call = apiService.getHomeData();
        call.enqueue(new Callback<HomeDataResponse>() {
            @Override
            public void onResponse(Call<HomeDataResponse> call, Response<HomeDataResponse> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    updateUI(response.body());
                } else {
                    showErrorState();
                }
            }

            @Override
            public void onFailure(Call<HomeDataResponse> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                showNetworkError();
            }
        });
    }

    private void updateUI(HomeDataResponse homeData) {
        userNameTextView.setText(homeData.getUserName());
        communityNameTextView.setText(homeData.getCommunityName());

        List<Pair<Integer, String>> quickActions = new ArrayList<>();
        if (homeData.getQuickActions() != null) {
            for (QuickAction action : homeData.getQuickActions()) {
                quickActions.add(new Pair<>(getDrawableRes(action.getIcon()), action.getName()));
            }
        }
        quickActionsAdapter.updateData(quickActions);

        List<Pair<Integer, String>> lifeServices = new ArrayList<>();
        if (homeData.getLifeServices() != null) {
            for (LifeService service : homeData.getLifeServices()) {
                lifeServices.add(new Pair<>(getDrawableRes(service.getIcon()), service.getName()));
            }
        }
        lifeServicesAdapter.updateData(lifeServices);
    }

    private int getDrawableRes(String iconName) {
        int resourceId = getResources().getIdentifier(iconName, "drawable", getPackageName());
        if (resourceId == 0) {
            return R.drawable.ic_launcher_foreground; // Default if not found
        }
        return resourceId;
    }

    private void showLoadingState() {
        loadingIndicator.setVisibility(View.VISIBLE);
        quickActionsRecyclerView.setVisibility(View.GONE);
        lifeServicesRecyclerView.setVisibility(View.GONE);
    }

    private void showNetworkError() {
        Toast.makeText(this, R.string.network_unavailable, Toast.LENGTH_SHORT).show();
        showErrorState();
    }

    private void showErrorState() {
        loadingIndicator.setVisibility(View.GONE);
        quickActionsRecyclerView.setVisibility(View.GONE);
        lifeServicesRecyclerView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    private void setupQuickActionButtons() {
        quickActionsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        quickActionsRecyclerView.setAdapter(quickActionsAdapter);
    }

    private void setupLifeServiceCards() {
        lifeServicesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        lifeServicesRecyclerView.setAdapter(lifeServicesAdapter);
    }
}
