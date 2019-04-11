package com.alick.commonlibrary.utils;

import okhttp3.Request;

public class LoggingInterceptor extends AbstractHttpInterceptor {
    @Override
    public void addHeader(Request.Builder builder) {
        builder.addHeader("platform","android");
    }
}