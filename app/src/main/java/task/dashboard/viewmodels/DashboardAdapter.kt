package task.dashboard.viewmodels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso

import java.util.ArrayList

import task.dashboard.R
import task.dashboard.models.DashboardItem
import task.dashboard.models.Item
import task.dashboard.viewmodels.DashboardAdapter.HeaderViewHolder
import task.dashboard.viewmodels.DashboardAdapter.ItemViewHolder

class DashboardAdapter(private val items: ArrayList<DashboardItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == DashboardItem.TYPE_ITEM) {
            val layoutInflater = LayoutInflater.from(parent.context)
            val listItem = layoutInflater.inflate(R.layout.item_data, parent, false)
            return ItemViewHolder(listItem)
        } else if (viewType == DashboardItem.TYPE_HEADER) {
            val layoutInflater = LayoutInflater.from(parent.context)
            val listItem = layoutInflater.inflate(R.layout.image_layout, parent, false)
            return HeaderViewHolder(listItem)
        }

        throw RuntimeException("Error Item Type Unknown")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val i = items[position] as Item

            holder.textView.text = i.name
            Picasso.get().load(i.photo).into(holder.imageView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].itemType
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var textView: TextView

        init {
            this.imageView = itemView.findViewById<View>(R.id.p) as ImageView
            this.textView = itemView.findViewById<View>(R.id.n) as TextView
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
