package com.personal.housework;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.housework.DTO.HouseWork;
import com.personal.housework.Detail.DetailView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{

    Context context;
    List<HouseWork> dataList;
    ItemClickListener mListener = null;

    // ItemClcikListener 리스너 객체 참조를 어댑터에 전달하는 메서드.
    public void setOnItemClickListener(ItemClickListener listener) {
        this.mListener = listener;
    }


    public CustomAdapter(Context context, List<HouseWork> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    // RecyclerView ViewHolder
    public class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView textName;
        CardView cv_clothWorkName;

        CustomViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.tv_textName);
            cv_clothWorkName = itemView.findViewById(R.id.cv_clothWorkName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        // 리스너 객체의 메서드 호출.
                        // 해당 클릭 포지션의 clothName + clothDesc 담아서 보내기.
                        Intent intent = new Intent(context, DetailView.class);
                        intent.putExtra("cateId", dataList.get(pos).getCate_id());
                        intent.putExtra("houseName", dataList.get(pos).getHouse_name());
                        intent.putExtra("houseDesc", dataList.get(pos).getHouse_desc());
                        context.startActivity(intent);
                    }
                }
            });

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
        holder.textName.setText(dataList.get(position).getHouse_name()); // 해당 포지션에 cloth_name 값 불러오기
        holder.cv_clothWorkName.setTag(dataList.get(position).getHouse_id());
    }

    // RecyclerView 사이즈.(길이)
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // 검색기능 Search Filter
    public void filterList(List<HouseWork> filteredList){
        dataList = filteredList;
        notifyDataSetChanged();
    }

}
