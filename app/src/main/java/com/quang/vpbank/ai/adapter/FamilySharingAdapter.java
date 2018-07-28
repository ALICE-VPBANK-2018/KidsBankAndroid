package com.quang.vpbank.ai.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quang.vpbank.ai.R;
import com.quang.vpbank.ai.model.FamilySharing;

import java.util.ArrayList;

public class FamilySharingAdapter extends RecyclerView.Adapter<FamilySharingAdapter.ViewHolder> {

    private ArrayList<FamilySharing> listFamilySharing;

    public FamilySharingAdapter(ArrayList<FamilySharing> listFamilySharing) {
        this.listFamilySharing = listFamilySharing;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_family_sharing, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvName.setText(listFamilySharing.get(i).getName());
        viewHolder.tvDate.setText(listFamilySharing.get(i).getDate());
        viewHolder.tvMoney.setText(listFamilySharing.get(i).getMoney());
    }

    @Override
    public int getItemCount() {
        return listFamilySharing.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvDate, tvMoney;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvMoney = itemView.findViewById(R.id.tvMoney);

        }
    }


}
