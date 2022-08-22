package com.personal.housework.Retrofit;

import com.personal.housework.DTO.HouseWork;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    // Cloth 에 대한 자료 읽어오기 Read.
    @GET("cloth_read.php")
    Call<List<HouseWork>> getAllCloth();
}
