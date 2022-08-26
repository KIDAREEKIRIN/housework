package com.personal.housework;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.personal.housework.DTO.HouseWork;
import com.personal.housework.Detail.DetailView;
import com.personal.housework.Retrofit.GetDataService;
import com.personal.housework.Retrofit.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WhatIsHouseWork extends AppCompatActivity {

    // 기존 22.08.23.(화) -> 변경 예정.
    RecyclerView recyclerView;
    List<HouseWork> myHouseWorkList;

    // Adapter
    CustomAdapter adapter;

    // 진행중 바
    ProgressDialog progressDialog;

    // 검색기능 구현하기./
    SearchView searchView;

    // 확장된 플로팅 액션 버튼
    ExtendedFloatingActionButton extended_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_house_work);

        searchView = findViewById(R.id.search_view);

        // 진행중 ProgressBar -> 너무 빨리 꺼짐 ( 데이터 숫자가 부족한 듯 )
        progressDialog = new ProgressDialog(WhatIsHouseWork.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        // 플로팅 액션 버튼
        extended_fab = findViewById(R.id.extended_fab);
        extend_fab(); // 추가하기 함수.

        myHouseWorkList = new ArrayList<>();

        // Retrofit 통신으로 서버에서 데이터베이스에 저장된 데이터 불러오기.
        // 레트로핏 인스턴스 생성을 해줍니다.
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<HouseWork>> call = service.getAllCloth();

        // enqueue로 비동기 통신을 싱행합니다.
        call.enqueue(new Callback<List<HouseWork>>() {
            @Override
            public void onResponse(Call<List<HouseWork>> call, Response<List<HouseWork>> response) {
                myHouseWorkList = response.body(); // 추가 해줘야 Retrofit 에서 받아온 데이터를 읽을 수 있음.
                progressDialog.dismiss(); // 진행중 바 사라짐.
                generateDataList(response.body());

            }

            @Override
            public void onFailure(Call<List<HouseWork>> call, Throwable t) {
                progressDialog.dismiss();// 진행중 바 사라짐.
                Toast.makeText(getApplicationContext(), "쏘리쏘리 다음에 다시 시도해주세요." +t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // 검색 기능 SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    // FAB 추가하기 클릭 시,
    private void extend_fab() {
        extended_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast 메세지.
//                Toast.makeText(getApplicationContext(), "추가하기 클릭!", Toast.LENGTH_SHORT).show();
                // Intent 로 상세페이지 열기.
                Intent detail = new Intent(getApplicationContext(), DetailView.class);
                startActivity(detail);
            }
        });
    }

    private void generateDataList(List<HouseWork> myHouseWorkList) {
        recyclerView = findViewById(R.id.rv_cloth);
        adapter = new CustomAdapter(this, myHouseWorkList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(WhatIsHouseWork.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @SuppressLint("NotifyDataSetChanged")
    private void filter(String newText) {
        List<HouseWork> filteredList = new ArrayList<>();
        for ( HouseWork item : myHouseWorkList) {
            if ( item.getCloth_name().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList); // filterList 내용 Adapter 에 반영하기.
        adapter.notifyDataSetChanged(); // List 데이터 갱신하기
    }

}