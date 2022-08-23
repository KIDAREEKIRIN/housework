package com.personal.housework;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.personal.housework.DTO.HouseWork;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    Context context;
    List<HouseWork> dataList;
    OnItemClickListener onItemClick;


    public CustomAdapter(Context context, List<HouseWork> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        CardView cv_clothWorkName;

        CustomViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.tv_textName);
            cv_clothWorkName = itemView.findViewById(R.id.cv_clothWorkName);

        }


    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.single_items, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        // 타이틀
        holder.textName.setText(dataList.get(position).getCloth_name()); // 해당 포지션에 cloth_name 값 불러오기
        Log.e(TAG, "onBindViewHolder: " + dataList.get(position).getCloth_name());
//        // 이미지 -> 현재 해당 이미지가 없음.
//        Glide.with(context)
//                .load(R.drawable.cloth)
//                .skipMemoryCache(true)
//                .circleCrop()
//                .skipMemoryCache(true)
//                .error(R.drawable.cloth)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(holder.);
        holder.cv_clothWorkName.setTag(dataList.get(position).getCloth_id());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    //
//    @NonNull
//    @Override
//    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.single_items, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
//        holder.textName.setText(list.get(position).getName());
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
    public void filterList(List<HouseWork> filteredList){
        dataList = filteredList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        onItemClick = onItemClickListener;
    }
}
