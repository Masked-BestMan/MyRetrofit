package com.example.zbm.myapplication;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiService {
    @GET("93app/data.do")
    Flowable<News> getNews(@QueryMap Map<String,String> map);
}
