package com.quang.vpbank.ai.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quang.vpbank.ai.R;
import com.quang.vpbank.ai.model.Message;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    private ArrayList<Message> listMessages;
    private View v;
    private OnItemClickListener mItemClickListener;

    public MessageAdapter(ArrayList<Message> listMessages) {
        this.listMessages = listMessages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0)
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_right, parent, false);
        else
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_left, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvMessage.setText(listMessages.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return listMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (listMessages.get(position).isBot()) return 1;
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvMessage;

        ViewHolder(View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tvMessage);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

}
