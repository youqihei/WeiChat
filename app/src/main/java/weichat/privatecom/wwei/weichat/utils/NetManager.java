package weichat.privatecom.wwei.weichat.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetManager {
    private static NetManager netManager;
    private Retrofit retrofit;
    private RetrofitServer retrofitServer;
    public static final String HOST = "http://192.168.0.178:8080";
    private static final int DEFAULT_TIMEOUT = 15;
    public NetManager()
    {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        retrofitServer = retrofit.create(RetrofitServer.class);
    }
    public static NetManager getInstance()
    {
        if(netManager==null)
        {
            synchronized (NetManager.class)
            {
                if(netManager==null)
                {
                    netManager = new NetManager();
                }
            }
        }
        return netManager;
    }
    public RetrofitServer getRetrofitServer()
    {
        return  retrofitServer;
    }
}
