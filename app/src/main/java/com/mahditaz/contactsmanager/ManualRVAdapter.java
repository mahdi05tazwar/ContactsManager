package com.mahditaz.contactsmanager;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ManualRVAdapter extends RecyclerView.Adapter<ManualRVAdapter.CustomViewHolder> {
    Context context;
    ArrayList<UserManualItem> items;

    public ManualRVAdapter(Context context, ArrayList<UserManualItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.manual_item_view, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        UserManualItem item = items.get(position);
        holder.itemImg.setImageResource(item.getImgRef());
        holder.itemTitle.setText(item.getTitle());
        holder.itemDescription.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImg;
        TextView itemTitle, itemDescription;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.itemImg);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemDescription = itemView.findViewById(R.id.itemDescription);
        }
    }
}
