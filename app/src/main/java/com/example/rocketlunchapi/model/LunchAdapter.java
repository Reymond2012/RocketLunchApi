package com.example.rocketlunchapi.model;

import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rocketlunchapi.R;

import java.util.ArrayList;
import java.util.List;

public class LunchAdapter extends RecyclerView.Adapter<LunchAdapter.ItemViewHolder> {
    private final List<Launch> data;

    public LunchAdapter(){
        this.data = new ArrayList<>();
    }
    public void setData (List<Launch> newData){
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lunch_display, viewGroup, false);
        return new ItemViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        Launch launch = data.get(position);
        itemViewHolder.tvLunches.setText(launch.getName());
        itemViewHolder.tvLocation.setText(launch.getLocation().toString());
        itemViewHolder.tvOffset.setText(launch.getRocket().toString());
        itemViewHolder.tvCount.setText(launch.getMissions().toString());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvLunches, tvLocation, tvOffset, tvCount;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLunches = itemView.findViewById(R.id.tvLunches);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvCount = itemView.findViewById(R.id.tvCount);
            tvOffset = itemView.findViewById(R.id.tvOffset);



        }
    }
}
