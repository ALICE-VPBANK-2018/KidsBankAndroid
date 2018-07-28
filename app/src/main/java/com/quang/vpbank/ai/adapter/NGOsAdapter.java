package com.quang.vpbank.ai.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quang.vpbank.ai.R;
import com.quang.vpbank.ai.model.NGOs;

import java.util.ArrayList;

public class NGOsAdapter extends RecyclerView.Adapter<NGOsAdapter.ViewHolder> {

    private ArrayList<NGOs> listNGOs;

    public NGOsAdapter(ArrayList<NGOs> listNGOs) {
        this.listNGOs = listNGOs;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ngos, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvName.setText(listNGOs.get(i).getName());
        viewHolder.tvDescription.setText(listNGOs.get(i).getDescription());
        viewHolder.imvAvatar.setImageResource(listNGOs.get(i).getAvatar());
    }

    @Override
    public int getItemCount() {
        return listNGOs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvDescription;
        ImageView imvAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            imvAvatar = itemView.findViewById(R.id.imvAvatar);
        }
    }


}
