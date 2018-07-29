package com.quang.vpbank.ai.utils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.quang.vpbank.ai.utils.AppConfig.URL_RANDOM;

public interface QuestionService {

    @GET(URL_RANDOM)
    Call<String> getRandomQuestion();

    @GET("https://kidsbank123.herokuapp.com/quiz/level={level}/offset=0/limit=5")
    Call<String> getRandomQuestion(@Path("level") int level);

    @GET("https://kidsbank123.herokuapp.com/quiz/q={id}/a={answer}")
    Call<String> sendAnswer(@Path("id") String id, @Path("answer") int answer);
}
