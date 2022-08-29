package com.personal.housework.CheckList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.personal.housework.R;

import java.util.List;

public class checkList extends AppCompatActivity implements CheckList_View {
    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    CheckList_Presenter presenter;
    CheckList_Adapter adapter;
    CheckList_Adapter.ItemClickListener itemClickListener;

    List<Note_checkList> note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        swipeRefresh = findViewById(R.id.swipe_refresh); // 스와이프.
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // 플로팅 액션 버튼.
        fab = findViewById(R.id.add);
        fab.setOnClickListener(view ->
                startActivityForResult(new Intent(this,Editor.class),
                        INTENT_ADD)
        );

        presenter = new CheckList_Presenter((CheckList_View) this);
        presenter.getData();

//         새로고침. -> 새로고침을 해야 항목이 추가된다는 것이 가장 큰 문제.
        // 스와이프를 하더라도 항목이 추가되도록 하기.
        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position) -> {
            int number = note.get(position).getNumber(); // 원본은 id
            String title = note.get(position).getTitle();
            String notes = note.get(position).getNote();
            int color = note.get(position).getColor();

            Intent intent = new Intent(this, Editor.class);
            intent.putExtra("number",number);
            intent.putExtra("title",title);
            intent.putExtra("note",notes);
            intent.putExtra("color",color); // 팔레트 색깔.
            startActivityForResult(intent, INTENT_EDIT);

//            Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == INTENT_ADD && resultCode == RESULT_OK) {
            presenter.getData(); // reload data
        } else if(requestCode == INTENT_EDIT && resultCode == RESULT_OK) {
            presenter.getData(); // reload data;
        }
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Note_checkList> notes) {
        adapter = new CheckList_Adapter(this, notes,itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        note = notes;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // 21.10.23.(토) 추가부분.
//
    // 생명주기를 통해 스와이프 하지 않아도 항목이 추가됨.
    @Override
    protected void onResume() {
        super.onResume();
        presenter.getData();

    }
}
