package com.alick.mvvmlearn.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 抽象的http拦截器
 * Created by cxw on 2016/9/30.
 */

public abstract class AbstractHttpInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
//    private boolean enableDebug;

    private String TAG = "AbstractHttpInterceptor";

    public AbstractHttpInterceptor() {
//        this.enableDebug = enableDebug;
    }


    public void addHeader(Request.Builder builder) {
        //默认空实现
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        addHeader(builder);
        Request request = builder.build();
        Response response = chain.proceed(request);


        RequestBody requestBody = request.body();

        Headers headers = request.headers();
        int headerSize = headers.size();
        StringBuffer sb = new StringBuffer();
        sb.append(" \n-----请求头-----\n");
        for (int i = 0; i < headerSize; i++) {
            sb.append(headers.name(i)).append(":").append(headers.value(i)).append("\n");
        }

        sb.append("-----请求参数-----\n");
        sb.append("url:").append(request.url()+"\n");
        Charset charset = UTF8;
        if (requestBody != null) {
            sb.append("Content-Type: ").append(requestBody.contentType());

            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            sb.append(buffer.readString(charset)).append("\n");
        }


        sb.append("-----响应内容-----\n");

//          响应体部分============================================

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        sb.append("responseCode:").append(response.code()).append(" ").append(response.message()).append("\n");

        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer responseBuffer = source.buffer();

        MediaType responseContentType = responseBody.contentType();
        if (responseContentType != null) {
            try {
                charset = responseContentType.charset(UTF8);
            } catch (UnsupportedCharsetException e) {
                BLog.e(TAG, "Couldn't decode the response body; charset is likely malformed.");
                BLog.e(TAG, "<-- END HTTP");

                return response;
            }
        }

        if (contentLength != 0) {
            sb.append(responseBuffer.clone().readString(charset));
            BLog.i(TAG, sb.toString());
        }


        return response;
    }

    /*========================set/get方法-begin========================*/

    /*========================set/get方法-end========================*/
}
