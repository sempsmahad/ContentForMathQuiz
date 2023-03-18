package com.kh69.contentformathquiz.remote;

public class APIUtils {
    private APIUtils() {
    }

//    public static final String API_URL = "http://192.168.43.28:5000/api/v1/";
    public static final String API_URL = "http://192.168.43.143:5000/api/v1/";
//    public static final String API_URL = "http://192.168.43.134:5000/api/v1/";
//    public static final String API_URL = "http://192.168.1.101:5000/api/v1/";
//    public static final String API_URL = "http://192.168.1.100:5000/api/v1/";

    public static QuestionService getQuestionService() {
        return RetrofitClient.getClient(API_URL).create(QuestionService.class);
    }

}
