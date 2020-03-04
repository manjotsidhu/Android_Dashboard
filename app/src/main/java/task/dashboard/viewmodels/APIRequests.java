package task.dashboard.viewmodels;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.GridLayoutManager.DefaultSpanSizeLookup;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import task.dashboard.R;
import task.dashboard.models.DashboardItem;
import task.dashboard.models.Header;
import task.dashboard.models.Item;
import task.dashboard.views.MainActivity;

public class APIRequests {
    private static final String JSON_URL = "https://pascolan-config.s3.us-east-2.amazonaws.com/android/v1/prod/Category/hi/categoryV2.json";

    private Context context;
    private ArrayList<DashboardItem> itemLists;
    private RecyclerView dashboardView;

    public APIRequests(Context c, RecyclerView d) {
        this.context = c;
        this.dashboardView= d;

        itemLists = new ArrayList<>();

        loadDashboardList();
    }

    private void loadDashboardList() {
        final ProgressBar progressBar = ((MainActivity) context).findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);

                        try {
                            JSONArray obj = new JSONArray(response);

                            for (int i = 0; i < obj.length(); i++) {
                                JSONObject itemObj = obj.getJSONObject(i);

                                if (itemObj.names().length() == 4) {
                                    Item item = new Item(itemObj.getInt("i"), new String(itemObj.getString("n").getBytes("ISO-8859-1"), "utf-8"), itemObj.getString("a"), itemObj.getString("p"));
                                    itemLists.add(item);
                                } else {
                                    Item item = new Item(itemObj.getInt("i"), new String(itemObj.getString("n").getBytes("ISO-8859-1"), "utf-8"), null, itemObj.getString("p"));
                                    itemLists.add(item);
                                }
                            }

                            itemLists.add(6, new Header(0, "Sample Image Here"));
                            final DashboardAdapter adapter = new DashboardAdapter(itemLists);

                            GridLayoutManager dashboardManager = new GridLayoutManager(context, 3);
                            dashboardManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    switch(adapter.getItemViewType(position)){
                                        case DashboardItem.TYPE_HEADER:
                                            return 3;

                                        case DashboardItem.TYPE_ITEM:
                                        default:
                                            return 1;
                                    }
                                }
                            });

                            dashboardView.setLayoutManager(dashboardManager);
                            dashboardView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
