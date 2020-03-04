package task.dashboard.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import task.dashboard.R;
import task.dashboard.models.Item;
import task.dashboard.viewmodels.APIRequests;
import task.dashboard.viewmodels.DashboardAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView dashboardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dashboardView = findViewById(R.id.dashboard_view);

        APIRequests api = new APIRequests(this, dashboardView);
    }
}
