package com.personal.housework.CheckList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.housework.R;

import java.util.List;

public class CheckList_Adapter extends RecyclerView.Adapter<CheckList_Adapter.RecyclerViewAdaper>{
    private Context context;
    private List<Note_checkList> notes;
    private ItemClickListener itemClickListener;

    public CheckList_Adapter(Context context, List<Note_checkList> notes, ItemClickListener itemClickListener) {
        this.context = context;
        this.notes = notes;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdaper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note, // 리사이클러뷰 풀로 보여주기.
                parent, false);
        return new RecyclerViewAdaper(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdaper holder, int position) {
        Note_checkList note = notes.get(position);
        holder.tv_title.setText(note.getTitle()); // 아이템 목록을 제목만 보이게 하기.
        holder.tv_note.setText(note.getNote());
        holder.tv_filename.setText(note.getDate());
        holder.card_item.setCardBackgroundColor(note.getColor());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class RecyclerViewAdaper extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_title, tv_note, tv_filename;
        CardView card_item;
        ItemClickListener itemClickListener;

        public RecyclerViewAdaper( View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.noteTitle); // 아이템 목록을 제목만 보이게 하기.
            tv_note = itemView.findViewById(R.id.noteNote);
            tv_filename = itemView.findViewById(R.id.filename);
            card_item = itemView.findViewById(R.id.card_item); // 카드뷰 형태로 표시함.

            this.itemClickListener = itemClickListener;
            card_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
