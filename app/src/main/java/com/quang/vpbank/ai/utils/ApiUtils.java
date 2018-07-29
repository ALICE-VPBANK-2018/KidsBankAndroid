package com.quang.vpbank.ai.utils;

public class ApiUtils {
    private ApiUtils() {
    }

    public static final String BASE_URL = "https://api.api.ai/v1/";

    public static AliceService getAliceService() {

        return RetrofitClient.getClient(BASE_URL).create(AliceService.class);
    }

    public static QuestionService getQuestionService() {

        return RetrofitClient.getClient(BASE_URL).create(QuestionService.class);
    }

}
