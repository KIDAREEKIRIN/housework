package com.personal.housework.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.personal.housework.DTO.HouseWork;
import com.personal.housework.R;
import com.personal.housework.Retrofit.GetDataService;
import com.personal.housework.Retrofit.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailView extends AppCompatActivity {

    // Layout 의 구성요소.
    EditText et_clothName, et_clothDesc;
    ImageView iv_clothPhoto;

    // 저장된 데이터 불러오기.
    List<HouseWork> myHouseWorkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        et_clothName = findViewById(R.id.et_clothName); // 가정일 이름.
        et_clothDesc = findViewById(R.id.et_clothDesc); // 가정일 설명하는 부분.

        iv_clothPhoto = findViewById(R.id.iv_clothPhoto); // 가정일 관련 사진.


        // Detail_View 의 속성
        // 1. 양식에 맞게 (데이터베이스에 저장된) 데이터를 불러온다.
        // 2. 양식에 맞게 (데이터베이스에 알맞게) 데이터를 추가한다.

        myHouseWorkList = new ArrayList<>();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<HouseWork>> call = service.getAllCloth();

        call.enqueue(new Callback<List<HouseWork>>() {
            @Override
            public void onResponse(Call<List<HouseWork>> call, Response<List<HouseWork>> response) {
                myHouseWorkList = response.body();
            }

            @Override
            public void onFailure(Call<List<HouseWork>> call, Throwable t) {

            }
        });
    }
}