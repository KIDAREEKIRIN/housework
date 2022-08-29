package com.personal.housework.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.personal.housework.R;

public class DetailView extends AppCompatActivity {

    // Layout 의 구성요소.
    TextView tv_cateName, tv_houseName, tv_houseDesc;
    ImageView iv_clothPhoto;
    // Intent 값.
    String houseName, houseDesc;
    Integer cate_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        tv_cateName = findViewById(R.id.tv_cateName);
        tv_houseName = findViewById(R.id.tv_houseName); // 가정일 이름.
        iv_clothPhoto = findViewById(R.id.iv_clothPhoto); // 가정일 관련 사진.
        tv_houseDesc = findViewById(R.id.tv_houseDesc); // 가정일 설명하는 부분.


        getSupportActionBar().setTitle("가정일"); // 액션바 Title 없애기.
        // Detail_View 의 속성
        // 1. 양식에 맞게 (데이터베이스에 저장된) 데이터를 불러온다.
        // 2. 양식에 맞게 (데이터베이스에 알맞게) 데이터를 추가한다.]

        // Intent 값 받기.
        Intent intent = getIntent();
        cate_id = intent.getIntExtra("cateId",0);
        houseName = intent.getStringExtra("houseName");
        houseDesc = intent.getStringExtra("houseDesc");
        // 가정일 이름 + 설명에 갖다 붙이기.
        tv_houseName.setText(houseName);
        tv_houseDesc.setText(houseDesc);
        switch(cate_id){
          case 1 :
              tv_cateName.setText("의생활");
            break;
            case 2:
                tv_cateName.setText("식생활");
                break;
            case 3:
                tv_cateName.setText("주생활");
                break;
            case 4:
                tv_cateName.setText("가정경제활동");
                break;
            case 5:
                tv_cateName.setText("가족돌보기활동");
                break;
            case 6:
                tv_cateName.setText("기타");
                break;
        }

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_editor, menu);
//        return true;
//    }
//
//    // 우측 상단의 체크버튼을 누르면 서버로 데이터 전송
//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.insert:
//                String clothName = tv_clothName.getText().toString().trim();
//                // 이미지 경로 입력하는 도중에 막힘 .22. 08. 26(금).
//                String clothDesc = tv_clothDesc.getText().toString().trim();
//
//                if (clothName.isEmpty()) {
//                    tv_clothName.setError("가정일을 작성하세요");
//                } else if (clothDesc.isEmpty()) {
//                    tv_clothDesc.setError("노트를 작성하세요.");
//                } else {
//                    // DB에 각 항목들 추가 -> BUT. 서버에 이미지 파일 업로드 안됨.
//                    insertWork(clothName,clothDesc);
//                    // 추가 이후 DetailView_afterPhoto로 이동.
//                    Intent intent = new Intent(getApplicationContext(), WhatIsHouseWork.class);
//                    startActivity(intent);
//
//                }
//
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }

//    // insertWork 함수
//    public void insertWork(String clothName, String clothDesc) {
//        //레트로핏으로 제목과 내용을 보냄 ->
//        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<HouseWork> call = service.insertWork(clothName,clothDesc);
//
//        call.enqueue(new Callback<HouseWork>() {
//            @Override
//            public void onResponse(Call<HouseWork> call, Response<HouseWork> response) {
//                // 서버에서 응답을 받아옴
//                if (response.isSuccessful() && response.body() != null) {
//                    HouseWork houseWork = response.body();
//                    Toast.makeText(getApplicationContext(), "서버 응답: " + houseWork.getResponse(), Toast.LENGTH_SHORT).show();
//                    Boolean success = response.body().getSuccess();
//                    String message = response.body().getMessage();
//                    // 입력성공시 서버에서 메시지를 받아와서 토스트로 출력
//                    if (success) {
//                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//                        finish(); // 메인액티비티로 돌아감
//
//                        Log.e("서버에서 받아온내용", message);
//                    }
//                    // 응답을 받아오지 못했을경우
//                } else {
//                    assert response.body() != null;
//                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<HouseWork> call, Throwable t) {
//                Toast.makeText(DetailView.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

}