package com.example.heimagirl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "1234";
    @BindView(R.id.list_view)
   ListView mlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  mlistview = (ListView) findViewById(R.id.list_view);
        ButterKnife.bind(this);
      //  sendRequest();
        sendSyncRequest();
    }

     private void sendSyncRequest() {
        //創建服務端
        OkHttpClient okHttpclient  = new OkHttpClient();
        //創建請求
        String url= "http://gank.io/api/data/福利/10/1";
        //Request他通常用構建著構建
        Request request =  new Request.Builder().get().url(url).build();
        //异步请求
        okHttpclient.newCall(request).enqueue(new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                   String result  = response.body().string();
                Log.d(TAG,"response"+result);
            }

        }) ;
    }


    private void sendRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //創建服務端
                OkHttpClient okHttpclient  = new OkHttpClient();
                //創建請求
                String url= "http://gank.io/api/data/福利/10/1";
                //Request他通常用構建著構建
                Request request =  new Request.Builder().get().url(url).build();
                // 请求创建好了便发送
                try {
                    okHttpclient.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //創建服務端

    }



}
