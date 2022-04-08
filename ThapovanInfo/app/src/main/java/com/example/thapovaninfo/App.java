package com.example.thapovaninfo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class App {

    private static App instance;

    public static App getInstance() {
        if (instance == null)
            instance = new App();
        return instance;
    }

    //TODO GET METHOD
    public void GetDetails(Callback b) throws IOException {
        get(Const.GetDetails, b);
    }

    private void get(String url, Callback b) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();
        client.newCall(request).enqueue(b);
    }

}