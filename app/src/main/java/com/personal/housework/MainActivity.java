package com.personal.housework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.personal.housework.DTO.Category;
import com.personal.housework.Retrofit.GetDataService;
import com.personal.housework.Retrofit.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_workCategory;
    List<Category> categoryList;

    Main_Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryList = new ArrayList<>();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Category>> call = service.getAllCategory();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                categoryList = response.body();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }

    private void generateDataList(List<Category> categoryList) {

        rv_workCategory = findViewById(R.id.rv_workCategory);
        adapter = new Main_Adapter(this, categoryList);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,6);
        rv_workCategory.setLayoutManager(gridLayoutManager);
        rv_workCategory.setAdapter(adapter);
    }

}