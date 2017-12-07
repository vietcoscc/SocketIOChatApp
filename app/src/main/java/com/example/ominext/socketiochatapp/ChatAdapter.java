package com.example.ominext.socketiochatapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ominext on 12/7/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ChatMessage> arrChatMessage;

    public ChatAdapter(ArrayList<ChatMessage> arrChatMessage) {
        this.arrChatMessage = arrChatMessage;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChatViewHolder chatViewHolder = (ChatViewHolder) holder;
        chatViewHolder.bind(arrChatMessage.get(position));
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvMessage)
        TextView tvMessage;

        public ChatViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(ChatMessage chatMessage) {
            tvName.setText(chatMessage.getFromName());
            tvMessage.setText(chatMessage.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return arrChatMessage.size();
    }

    public void addItem(ChatMessage chatMessage) {
        arrChatMessage.add(chatMessage);
        notifyItemInserted(arrChatMessage.size() - 1);
    }

    public void removeItem(int position) {
        arrChatMessage.remove(position);
        notifyItemRemoved(position);
    }
}
