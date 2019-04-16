package com.minorproject.minorproject.adapters;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.minorproject.minorproject.R;
import com.minorproject.minorproject.models.Message;

import java.sql.Time;
import java.util.List;

public class MessaageAdapter extends RecyclerView.Adapter<MessaageAdapter.MessageViewHolder> {

    private Context context;
    private List<Message> messageList;

    public MessaageAdapter(Context context, List<Message> messageList) {
        this.context = context;
        this.messageList = messageList;
    }


    @Override
    public MessaageAdapter.MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.message , parent , false );
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessaageAdapter.MessageViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.textView2.setText(message.name);
        Glide.with(context).load(message.imageUrl).into(holder.imageView);
        holder.textView1.setText(message.message);
    }

    @Override
    public int getItemCount() {
       return messageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView textView1,textView2,textView3;
        ImageView imageView,imageView1;

        public MessageViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.messageTextView);
            imageView = itemView.findViewById(R.id.messageImageView);
            textView2 = itemView.findViewById(R.id.messengerTextView);
            imageView1 = itemView.findViewById(R.id.messengerImageView);


        }
    }
}
