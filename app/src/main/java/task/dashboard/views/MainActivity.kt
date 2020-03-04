package task.dashboard.views

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

import android.os.Bundle

import task.dashboard.R
import task.dashboard.viewmodels.APIRequests

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dashboardView = findViewById<RecyclerView>(R.id.dashboard_view)

        val api = APIRequests(this, dashboardView)
    }
}
