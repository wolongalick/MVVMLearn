package com.alick.mvvmlearn.utils;

import okhttp3.Request;

public class LoggingInterceptor extends AbstractHttpInterceptor {
    @Override
    public void addHeader(Request.Builder builder) {
        builder.addHeader("platform","android");
    }
}