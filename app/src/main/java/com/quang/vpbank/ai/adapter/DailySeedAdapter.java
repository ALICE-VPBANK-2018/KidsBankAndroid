package com.quang.vpbank.ai.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quang.vpbank.ai.R;
import com.quang.vpbank.ai.model.DailySeed;

import java.util.ArrayList;

public class DailySeedAdapter extends RecyclerView.Adapter<DailySeedAdapter.ViewHolder> {

    private ArrayList<DailySeed> listDailySeed;

    public DailySeedAdapter(ArrayList<DailySeed> listDailySeed) {
        this.listDailySeed = listDailySeed;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_daily_seed, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvName.setText(listDailySeed.get(i).getName());
        viewHolder.tvPromote.setText(listDailySeed.get(i).getPromote());
        viewHolder.tvDetail.setText(listDailySeed.get(i).getDetail());
    }

    @Override
    public int getItemCount() {
        return listDailySeed.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPromote, tvDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPromote = itemView.findViewById(R.id.tvPromote);
            tvDetail = itemView.findViewById(R.id.tvDetail);

        }
    }


}
