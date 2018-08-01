package com.example.zbm.myapplication;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class NewsPresenter implements BasePresenter {
    private Iview iv;
    private Disposable subscriber;

    public void attachView(Iview iv) {
        this.iv = iv;
    }

    public void detachView() {
        if (iv != null) {
            iv = null;
        }
        if (!subscriber.isDisposed()){
            subscriber.dispose();
        }
    }

    @Override
    public void getData(Map<String, String> map) {
        NewsModel model = new NewsModel(this);
        model.getData(map);
    }

    public void getNews(Flowable<News> flowable) {

        subscriber = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<News>() {
                    @Override
                    public void accept(News news) throws Exception {
                        if (news != null) {
                            iv.onSuccess(news);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        iv.onFailed((Exception) throwable);
                    }
                });
//                .subscribeWith(new DisposableSubscriber<News>() {
//                    @Override
//                    public void onNext(News news) {
//                        if (news != null) {
//                            iv.onSuccess(news);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        iv.onFailed((Exception) t);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }
}
