package com.personal.housework.Retrofit;

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
    Call<List<HouseWork>> getAllCloth();

    // 가정일 추가하기 Insert
    @FormUrlEncoded
    @POST
    Call<HouseWork> insertWork(@Field("cloth_name") String cloth_name,
                               @Field("cloth_photo_path") String cloth_photo_path,
                               @Field("cloth_desc") String cloth_desc);
}
