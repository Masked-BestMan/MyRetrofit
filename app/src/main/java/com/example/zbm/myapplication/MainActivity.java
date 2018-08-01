package com.example.zbm.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity implements extends AppCompatActivity {
    @BindView(R.id.tv)
    TextView tv;

    private NewsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.detachView();
        }
    }

    public void getData(View view){
        Map<String, String> map = new HashMap<>();
        map.put("channelId","0");
        map.put("startNum","0");
        presenter = new NewsPresenter();
        presenter.getData(map);
        presenter.attachView(new Iview() {
            @Override
            public void onSuccess(Object o) {
                if (o instanceof News){
                    News news = (News) o;
                    tv.setText(news.getResult());
                    //Log.i("zzz", "onSuccess: "+news.getResult());
                }
            }

            @Override
            public void onFailed(Exception e) {
                //Log.i("zzz", "onSuccess: "+e.getMessage());
                tv.setText(e.getMessage());
            }
        });
    }
}
