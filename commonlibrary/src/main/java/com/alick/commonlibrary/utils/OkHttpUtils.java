package com.alick.commonlibrary.utils;

import com.alick.commonlibrary.base.bean.BaseResponse;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.repository.remote
 * @title:
 * @description: TODO
 * @date 2019/4/9 9:44
 */
public class OkHttpUtils {

    public static final String BASE_URL = "https://api.github.com/";

    private OkHttpClient client = null;


    private static OkHttpUtils instance = null;

    private OkHttpUtils() {

    }

    private OkHttpClient getOkHttpClient() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(new LoggingInterceptor())
                    .build();
        }
        return client;
    }

    public static OkHttpUtils getInstance() {
        if (instance == null) {
            synchronized (OkHttpUtils.class) {
                if (instance == null) {
                    instance = new OkHttpUtils();
                }
            }
        }
        return instance;
    }


    public interface OkCallback<Data> {
        void onSuccess(BaseResponse<Data> baseResponse);

        void onFail(Throwable throwable);
    }

    public <Data> void requestGet(String url, Map<String, Object> params, final OkCallback<Data> okCallback) {
        Request.Builder builder = new Request.Builder();
        Request request;
        if (params == null || params.size() == 0) {
            builder.url(url);
            request = builder.get().build();
        } else {
            Request.Builder requestBuild = new Request.Builder();

            HttpUrl.Builder httpUrlBuilder =HttpUrl.parse(url).newBuilder();

            Set<Map.Entry<String, Object>> entries = params.entrySet();

            for (Map.Entry<String, Object> map : entries) {
                httpUrlBuilder.addQueryParameter(map.getKey(), String.valueOf(map.getValue()));
            }

            HttpUrl httpUrl = httpUrlBuilder.build();
            request = requestBuild.url(httpUrl).build();
        }

        Call call = getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (!response.isSuccessful()) {
                    okCallback.onFail(new Throwable("网络请求失败"));
                    return;
                }

                ResponseBody body = response.body();
                if (body == null) {
                    okCallback.onFail(new Throwable("解析内容为空"));
                    return;
                }

                try {
                    String string = body.string();
                    Type[] types = ((ParameterizedType) okCallback.getClass().getGenericInterfaces()[0]).getActualTypeArguments();
                    Type type = types[0];
                    if(type.toString().contains("java.util.List")){
                        Class aClass= (Class)((ParameterizedType) type).getActualTypeArguments()[0];
                        Data data= (Data) JsonUtils.parseJson2List(string, aClass);
                        BaseResponse<Data> baseResponse=new BaseResponse<>(data);
                        okCallback.onSuccess(baseResponse);
                    }else {
                        Data data = JsonUtils.parseJson2Bean(string, type);
                        BaseResponse<Data> baseResponse=new BaseResponse<>(data);
                        okCallback.onSuccess(baseResponse);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    okCallback.onFail(new Throwable("解析内容失败"));
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                okCallback.onFail(e);
            }

        });
    }

}
