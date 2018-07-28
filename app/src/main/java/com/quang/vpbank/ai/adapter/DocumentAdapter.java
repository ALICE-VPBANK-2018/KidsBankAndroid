package com.quang.vpbank.ai.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quang.vpbank.ai.R;

import java.util.ArrayList;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder> {

    private ArrayList<String> listName;

    public DocumentAdapter(ArrayList<String> listName) {
        this.listName = listName;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_document, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvName.setText(listName.get(i));
    }

    @Override
    public int getItemCount() {
        return listName.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }


}
