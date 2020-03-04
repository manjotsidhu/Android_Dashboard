package task.dashboard.viewmodels

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.DefaultSpanSizeLookup
import androidx.recyclerview.widget.RecyclerView

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.io.UnsupportedEncodingException
import java.util.ArrayList

import task.dashboard.R
import task.dashboard.models.DashboardItem
import task.dashboard.models.Header
import task.dashboard.models.Item
import task.dashboard.views.MainActivity
import java.nio.charset.Charset

class APIRequests(private val context: Context, private val dashboardView: RecyclerView) {
    private val itemLists: ArrayList<DashboardItem> = ArrayList()

    init {

        loadDashboardList()
    }

    private fun loadDashboardList() {
        val progressBar = (context as MainActivity).findViewById<ProgressBar>(R.id.progressBar)

        progressBar.visibility = View.VISIBLE

        val stringRequest = StringRequest(Request.Method.GET, JSON_URL,
                Response.Listener { response ->
                    progressBar.visibility = View.INVISIBLE

                    try {
                        val obj = JSONArray(response)

                        for (i in 0 until obj.length()) {
                            val itemObj = obj.getJSONObject(i)

                            if (itemObj.names().length() == 4) {
                                val item = Item(itemObj.getInt("i"), String(itemObj.getString("n").toByteArray(charset("ISO-8859-1")), Charset.forName("utf-8")), itemObj.getString("a"), itemObj.getString("p"))
                                itemLists.add(item)
                            } else {
                                val item = Item(itemObj.getInt("i"), String(itemObj.getString("n").toByteArray(charset("ISO-8859-1")), Charset.forName("utf-8")), "", itemObj.getString("p"))
                                itemLists.add(item)
                            }
                        }

                        itemLists.add(6, Header(0, "Sample Image Here"))
                        val adapter = DashboardAdapter(itemLists)

                        val dashboardManager = GridLayoutManager(context, 3)
                        dashboardManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                            override fun getSpanSize(position: Int): Int {
                                when (adapter.getItemViewType(position)) {
                                    DashboardItem.TYPE_HEADER -> return 3

                                    DashboardItem.TYPE_ITEM -> return 1
                                    else -> return 1
                                }
                            }
                        }

                        dashboardView.layoutManager = dashboardManager
                        dashboardView.adapter = adapter

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    } catch (e: UnsupportedEncodingException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error -> Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show() })

        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }

    companion object {
        private val JSON_URL = "https://pascolan-config.s3.us-east-2.amazonaws.com/android/v1/prod/Category/hi/categoryV2.json"
    }
}
