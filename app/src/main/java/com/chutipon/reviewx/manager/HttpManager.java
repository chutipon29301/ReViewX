package com.chutipon.reviewx.manager;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.manager.http.ApiService;
import com.chutipon.reviewx.util.Contextor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 9/12/2017 AD.
 */

public class HttpManager {
    private static  HttpManager instance;
    private ApiService apiService;

    private HttpManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Contextor.getInstance().getContext().getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static HttpManager getInstance(){
        if (instance == null){
            instance = new HttpManager();
        }
        return instance;
    }

    public ApiService getApiService(){
        return apiService;
    }


}
