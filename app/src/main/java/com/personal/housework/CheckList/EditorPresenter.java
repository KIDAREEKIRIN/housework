package com.personal.housework.CheckList;

import androidx.annotation.NonNull;

import com.personal.housework.Retrofit.GetDataService;
import com.personal.housework.Retrofit.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenter {

    private EditorView view;

    public EditorPresenter(EditorView view) {
        this.view = view;
    }

    // 저장하기.
    void saveNote(final String title, final String note, final int color) {

        view.showProgress();

        // HTTP 통신.
        GetDataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(GetDataService.class);
        Call<Note_checkList> call = service.saveNote(title, note, color);

        call.enqueue(new Callback<Note_checkList>() {
            @Override
            public void onResponse(@NonNull Call<Note_checkList> call, @NonNull Response<Note_checkList> response) {
                view.hideProgress();

                if(response.isSuccessful() && response.body() != null) {

                    Boolean success = response.body().getSuccess();
                    if(success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Note_checkList> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }

    // 수정하기.
    void updateNote(int number, String title, String note, int color) {

        view.showProgress();
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<Note_checkList> call = service.updateNote(number, title, note, color);
        call.enqueue(new Callback<Note_checkList>() {
            @Override
            public void onResponse(@NonNull Call<Note_checkList> call,@NonNull Response<Note_checkList> response) {
                view.hideProgress();
                if ( response.isSuccessful() && response.body() != null) {

                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<Note_checkList> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    // 삭제하기.
    void deleteNote(int number) {

        view.showProgress();
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Note_checkList> call = service.deleteNote(number);
        call.enqueue(new Callback<Note_checkList>() {
            @Override
            public void onResponse(@NonNull Call<Note_checkList> call,@NonNull Response<Note_checkList> response) {
                view.hideProgress();
                if(response.isSuccessful() && response.body() != null) {
                    view.onRequestSuccess(response.body().getMessage());
                } else {
                    view.onRequestError(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Note_checkList> call,@NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }
}
