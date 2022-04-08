package com.example.thapovaninfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thapovaninfo.R;
import com.example.thapovaninfo.model.Model_mredis;

import java.util.ArrayList;

public class Adapter_redis extends RecyclerView.Adapter<Adapter_redis.QrViewHolder> {
    private ArrayList<Model_mredis> itemList;
    private Context context;
    public Adapter_redis(ArrayList<Model_mredis> list) {
        this.itemList = list;
    }

    @NonNull
    @Override
    public QrViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_adapter_list,viewGroup,false);
        context=viewGroup.getContext();
        return new QrViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QrViewHolder holder, int i) {

        if(itemList.get(i).getType().equals("true")){
            holder.ll_true.setVisibility(View.VISIBLE);
            holder.ll_false.setVisibility(View.GONE);
        }else {
            holder.ll_true.setVisibility(View.GONE);
            holder.ll_false.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class QrViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView Heading_tv;
        LinearLayout ll_false;
        LinearLayout ll_true;
        public QrViewHolder(@NonNull View itemView) {
            super(itemView);
            Heading_tv = itemView.findViewById(R.id.Heading_tv);
            ll_false = itemView.findViewById(R.id.ll_false);
            ll_true = itemView.findViewById(R.id.ll_true);

        }
    }
}
