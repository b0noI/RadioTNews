package com.kovalevskyi.radiot.news.model;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

public interface NewsService {

    public static final String NEWS_ENDPOINT = "http://master.radio-t.com:8778";

    @GET("/api/v1/news/active")
    Observable<News> getActiveNews();

    @GET("/api/v1/news")
    Observable<List<News>> getAllNews();

}
