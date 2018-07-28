package com.quang.vpbank.ai.utils;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AliceService {

    @Headers("Content-Type: application/json")
    @POST("query")
    Call<String> sendMessage(@Header("Authorization") String token, @Body String body);
}
