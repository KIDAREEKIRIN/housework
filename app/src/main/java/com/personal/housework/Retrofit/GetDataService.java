package com.personal.housework.Retrofit;

import com.personal.housework.CheckList.Note_checkList;
import com.personal.housework.DTO.Category;
import com.personal.housework.DTO.HouseWork;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetDataService {

    // Cloth 에 대한 자료 읽어오기 Read.
    @GET("cloth_read.php")
    Call<List<HouseWork>> getAllHouseWork();

    // 가정일 추가하기 Insert
    @FormUrlEncoded
    @POST("cloth_insert.php")
    Call<HouseWork> insertWork(@Field("cloth_name") String cloth_name,
                               @Field("cloth_desc") String cloth_desc);

    @GET("category_read.php")
    Call<List<Category>> getAllCategory();

    // 업무선택 관련 내용.
    // 저장하기.
    @FormUrlEncoded
    @POST("checkList_save.php")
    Call<Note_checkList> saveNote(@Field("title") String title,
                                  @Field("note") String note,
                                  @Field("color") int color);


    // 노트 불러오기.
    @GET("notes.php")
    Call<List<Note_checkList>> getNotes();


    // 수정하기.
    @FormUrlEncoded
    @POST("update.php")
    Call<Note_checkList> updateNote(@Field("number") int number,
                                    @Field("title") String title,
                                    @Field("note") String note,
                                    @Field("color") int color);

    // 삭제하기.
    @FormUrlEncoded
    @POST("delete.php")
    Call<Note_checkList> deleteNote(@Field("number") int number);
}
