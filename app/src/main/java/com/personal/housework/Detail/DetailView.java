package com.personal.housework.Detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.personal.housework.DTO.HouseWork;
import com.personal.housework.R;
import com.personal.housework.Retrofit.GetDataService;
import com.personal.housework.Retrofit.RetrofitClientInstance;
import com.personal.housework.WhatIsHouseWork;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailView extends AppCompatActivity {

    // Layout 의 구성요소.
    EditText et_clothName, et_clothDesc;
    ImageView iv_clothPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        et_clothName = findViewById(R.id.et_clothName); // 가정일 이름.
        iv_clothPhoto = findViewById(R.id.iv_clothPhoto); // 가정일 관련 사진.
        et_clothDesc = findViewById(R.id.et_clothDesc); // 가정일 설명하는 부분.


        getSupportActionBar().setTitle(""); // 액션바 Title 없애기.
        // Detail_View 의 속성
        // 1. 양식에 맞게 (데이터베이스에 저장된) 데이터를 불러온다.
        // 2. 양식에 맞게 (데이터베이스에 알맞게) 데이터를 추가한다.
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        return true;
    }

    // 우측 상단의 체크버튼을 누르면 서버로 데이터 전송
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.insert:
                String clothName = et_clothName.getText().toString().trim();
                // 이미지 경로 입력하는 도중에 막힘 .22. 08. 26(금).
                String clothPhotoPath = iv_clothPhoto.getText().toString().trim();
                String clothDesc = et_clothDesc.getText().toString().trim();

                if (clothName.isEmpty()) {
                    et_clothName.setError("가정일을 작성하세요");
                } else if (clothDesc.isEmpty()) {
                    et_clothDesc.setError("노트를 작성하세요.");
                } else {
                    insertWork(clothName,cloth_photo_path, clothDesc);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    // insertWork 함수
    private void insertWork(String clothName, String cloth_photo_path,String clothDesc) {
        //레트로핏으로 제목과 내용을 보냄 ->
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<HouseWork> call = service.insertWork(clothName, cloth_photo_path,clothDesc);

        call.enqueue(new Callback<HouseWork>() {
            @Override
            public void onResponse(Call<HouseWork> call, Response<HouseWork> response) {
                // 서버에서 응답을 받아옴
                if (response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    // 입력성공시 서버에서 메시지를 받아와서 토스트로 출력
                    if (success) {
                        Toast.makeText(DetailView.this, message, Toast.LENGTH_SHORT).show();
                        finish(); // 메인액티비티로 돌아감

                        Log.e("서버에서 받아온내용", message);
                    }
                    // 응답을 받아오지 못했을경우
                } else {
                    assert response.body() != null;
                    Toast.makeText(DetailView.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<HouseWork> call, Throwable t) {
                Toast.makeText(DetailView.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}