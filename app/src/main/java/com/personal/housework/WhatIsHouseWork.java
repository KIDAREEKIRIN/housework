package com.personal.housework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class WhatIsHouseWork extends AppCompatActivity {

    RecyclerView recyclerView;
    List<HouseWork> myHouseWorkList;
    CustomAdapter customAdapter;

    // 검색기능 구현하기./
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_house_work);

        searchView = findViewById(R.id.search_view);


        displayItems();


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

    private void filter(String newText) {
        List<HouseWork> filteredList = new ArrayList<>();
        // 향상된(?) for문 -> (변수타입 변수이름 : 배열이름)
        for (HouseWork item : myHouseWorkList) {
            // 반복할 코드 , Array or ArrayList OK, 수정 불가.
            if(item.getName().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(item);
            }
        }
        customAdapter.filterList(filteredList);
    }

    private void displayItems() {

        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        // 새로운 ArrayList 생성
        myHouseWorkList = new ArrayList<>();

        myHouseWorkList.add(new HouseWork("세탁하기"));
        myHouseWorkList.add(new HouseWork("빨래 널기"));
        myHouseWorkList.add(new HouseWork("빨래 개기"));
        myHouseWorkList.add(new HouseWork("옷장 정리하기"));
        myHouseWorkList.add(new HouseWork("옷 수선하기"));
        myHouseWorkList.add(new HouseWork("다림질하기"));

        customAdapter = new CustomAdapter(this, myHouseWorkList);
        recyclerView.setAdapter(customAdapter);

    }
}