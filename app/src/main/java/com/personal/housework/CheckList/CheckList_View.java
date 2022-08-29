package com.personal.housework.CheckList;

import java.util.List;

public interface CheckList_View {

    void showLoading();
    void hideLoading();
    void onGetResult(List<Note_checkList> notes);
    void onErrorLoading(String message);
}
