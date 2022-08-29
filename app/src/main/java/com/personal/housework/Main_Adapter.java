package com.personal.housework;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.housework.DTO.Category;

import java.util.List;

public class Main_Adapter extends RecyclerView.Adapter<Main_Adapter.MainViewHolder> {

    Context context;
    List<Category> categoryList;
    ItemClickListener cateListener = null;

    // ItemClcikListener 리스너 객체 참조를 어댑터에 전달하는 메서드.
    public void setOnItemClickListener(ItemClickListener listener) {
        this.cateListener = listener;
    }

    public Main_Adapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        TextView tv_cateName;
        ImageView iv_cateImage;
        CardView cv_category;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_cateName = itemView.findViewById(R.id.tv_cateName);
            iv_cateImage = itemView.findViewById(R.id.iv_cateImage);
            cv_category = itemView.findViewById(R.id.cv_category);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(context.getApplicationContext(), WhatIsHouseWork.class);
                        intent.putExtra("cate_id",categoryList.get(pos).getCate_id());
                        context.startActivity(intent);
                    }
                }
            });

        }
    }


    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.main_items, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.tv_cateName.setText(categoryList.get(position).getCate_name());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


}
