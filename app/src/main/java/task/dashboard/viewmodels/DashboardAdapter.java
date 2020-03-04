package task.dashboard.viewmodels;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import task.dashboard.R;
import task.dashboard.models.DashboardItem;
import task.dashboard.models.Item;

public class DashboardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<DashboardItem> items;

    public DashboardAdapter(ArrayList<DashboardItem> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == DashboardItem.TYPE_ITEM) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.item_data, parent, false);
            ItemViewHolder viewHolder = new ItemViewHolder(listItem);
            return viewHolder;
        } else if (viewType == DashboardItem.TYPE_HEADER) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem = layoutInflater.inflate(R.layout.image_layout, parent, false);
            HeaderViewHolder viewHolder = new HeaderViewHolder(listItem);
            return viewHolder;
        }

        throw new RuntimeException("Error Item Type Unknown");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            final Item i = (Item) items.get(position);

            ItemViewHolder h = (ItemViewHolder) holder;
            h.textView.setText(i.getName());
            Picasso.get().load(i.getPhoto()).into(h.imageView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.p);
            this.textView = (TextView) itemView.findViewById(R.id.n);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
