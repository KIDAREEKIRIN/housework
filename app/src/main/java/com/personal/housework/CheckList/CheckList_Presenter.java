package com.personal.housework.CheckList;

import androidx.annotation.NonNull;

import com.personal.housework.Retrofit.GetDataService;
import com.personal.housework.Retrofit.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckList_Presenter {

    private CheckList_View view;

    public CheckList_Presenter(CheckList_View view) {
        this.view = view;
    }

    void getData() {
        view.showLoading();
        // 서버에 요청하기.

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Note_checkList>> call = service.getNotes();

        call.enqueue(new Callback<List<Note_checkList>>() {
            @Override
            public void onResponse(@NonNull Call<List<Note_checkList>> call, @NonNull Response<List<Note_checkList>> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Note_checkList>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
