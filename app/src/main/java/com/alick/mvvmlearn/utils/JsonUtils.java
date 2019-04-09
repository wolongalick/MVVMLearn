package com.alick.mvvmlearn.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * @author 崔兴旺
 * @package com.alick.mvvmlearn.utils
 * @title:
 * @description: TODO
 * @date 2019/4/9 10:01
 */
public class JsonUtils {
    private static Gson gson=new Gson();

    public static <Entity> Entity parseJson(String json, Type type){
        return gson.fromJson(json,type);
    }


}
