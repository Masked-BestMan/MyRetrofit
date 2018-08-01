package com.example.zbm.myapplication;

import java.util.Map;

import io.reactivex.Flowable;

public class NewsModel implements IModel{
    private NewsPresenter presenter;
    public NewsModel(NewsPresenter presenter){
        this.presenter = presenter;
    }
    @Override
    public void getData(Map<String, String> map) {
        Flowable<News> flowable = RetrofitUtils.getInstance().getApiService().getNews(map);
        presenter.getNews(flowable);
    }
}
