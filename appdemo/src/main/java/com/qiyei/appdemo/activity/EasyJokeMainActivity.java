package com.qiyei.appdemo.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.qiyei.appdemo.net.DiscoverListReq;
import com.qiyei.appdemo.net.RetrofitApiService;
import com.qiyei.framework.activity.BaseSkinActivity;
import com.qiyei.framework.titlebar.CommonTitleBar;
import com.qiyei.sdk.https.api.HttpManager;
import com.qiyei.sdk.https.api.listener.IHttpListener;
import com.qiyei.sdk.https.api.request.HttpGetRequest;
import com.qiyei.sdk.log.LogManager;
import com.qiyei.sdk.util.ToastUtil;

import com.qiyei.appdemo.R;
import com.qiyei.appdemo.model.DiscoverListResult;

public class EasyJokeMainActivity extends BaseSkinActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
        initData();
        initView();
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_easy_joke_main);
    }

    @Override
    protected void initView() {
        CommonTitleBar commonNavigationBar = new CommonTitleBar.Builder(this)
                .setTitle("主界面")
                .setRightText("投稿")
                .setRightClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.showLongToast("点击了右边");
                    }
                })
                .build();
    }

    @Override
    protected void initData() {
        new HttpManager().execute(getSupportFragmentManager(),buildRequest(), new IHttpListener<DiscoverListResult>() {

            @Override
            public void onSuccess(DiscoverListResult response) {
               LogManager.d(TAG,"name --> "+response.getData().getCategories().getName());
            }

            @Override
            public void onFailure(Exception exception) {
                LogManager.d(TAG,exception.getMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * retrofit请求
     * @return
     */
    private HttpGetRequest buildRequest(){
        DiscoverListReq req = new DiscoverListReq();
        req.setIid("6152551759");
        req.setAid("7");
        HttpGetRequest<DiscoverListReq> request = new HttpGetRequest(req);
        request.setBaseUrl("http://is.snssdk.com/2/essay/");
        request.setPathUrl("discovery/v3/");
        request.setCache(true);
        request.setApiClazz(RetrofitApiService.class);
        return request;
    }

}
