/**
 *
 */
package com.alick.mvvmlearn.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 功能: json解析类
 * 作者: 崔兴旺
 * 日期: 2019/4/9
 */
public class JsonUtils {
    private static Gson gson;


    private static Gson getGson() {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            TypeAdapter<String> STRING = new TypeAdapter<String>() {
                public String read(JsonReader reader) throws IOException {
                    if (reader.peek() == JsonToken.NULL) {
                        reader.nextNull();
                        return "";
                    }
                    return reader.nextString();
                }

                public void write(JsonWriter writer, String value) throws IOException {
                    if (value == null) {
                        // 在这里处理null改为空字符串
                        writer.value("");
                        return;
                    }
                    writer.value(value);
                }
            };
            //注册自定义String的适配器
            gsonBuilder.registerTypeAdapter(String.class, STRING);
            gson = gsonBuilder.create();
        }
        return gson;
    }

    public static Object parseMapIterabletoJSON(Object object) throws JSONException {
        if (object instanceof Map) {
            JSONObject json = new JSONObject();
            Map map = (Map) object;
            for (Object key : map.keySet()) {
                json.put(key.toString(), parseMapIterabletoJSON(map.get(key)));
            }
            return json;
        } else if (object instanceof Iterable) {
            JSONArray json = new JSONArray();
            for (Object value : ((Iterable) object)) {
                json.put(value);
            }
            return json;
        } else {
            return object;
        }
    }

    public static String parseBean2json(Object obj) {
        return getGson().toJson(obj);
    }

    public static boolean isEmptyObject(JSONObject object) {
        return object.names() == null;
    }

    public static Map<String, Object> getMap(JSONObject object, String key)
            throws JSONException {
        return toMap(object.getJSONObject(key));
    }

    public static Map<String, Object> toMap(JSONObject object)
            throws JSONException {

        Map<String, Object> map = new HashMap();
        Iterator keys = object.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            map.put(key, fromJson(object.get(key)));
        }
        return map;
    }

    public static Map<String, String> parseMap(JSONObject object)
            throws JSONException {
        Map<String, String> map = new HashMap();
        try {
            Iterator keys = object.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                map.put(key, fromJson(object.get(key)).toString());
            }
            return map;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static List toList(JSONArray array) throws JSONException {
        List list = new ArrayList();
        for (int i = 0; i < array.length(); i++) {
            list.add(fromJson(array.get(i)));
        }
        return list;
    }

    private static Object fromJson(Object json) throws JSONException {
        if (json == JSONObject.NULL) {
            return null;
        } else if (json instanceof JSONObject) {
            return toMap((JSONObject) json);
        } else if (json instanceof JSONArray) {
            return toList((JSONArray) json);
        } else {
            return json;
        }
    }

    public static <T> List<T> parseRootJson2List(String json, Class<T> clazz, String listKey) throws JSONException {
        return parseJson2List(new JSONObject(json).getJSONObject("data").getJSONArray(listKey).toString(), clazz);
    }

    public static <T> List<T> parseRootJson2List(String json, Class<T> clazz) throws JSONException {
        return parseJson2List(new JSONObject(json).getJSONArray("data").toString(), clazz);
    }

    public static <T> List<T> parseJson2List(String json, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        if (TextUtils.isEmpty(json)) {
            return list;
        }

        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (JsonElement element : array) {
            try {
                list.add(getGson().fromJson(element, clazz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static <T> List<T> parseJson2List(String json, Type type) {
        List<T> list = new ArrayList<>();
        if (TextUtils.isEmpty(json)) {
            return list;
        }

        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (JsonElement element : array) {
            try {
                T t=getGson().fromJson(element, type);
                list.add(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }




    public static <T> T parseJson2Bean(String json, Class<T> clazz) {
        return getGson().fromJson(json, clazz);
    }

    public static <T> T parseRootJson2Bean(String json, Class<T> clazz) throws JSONException {
        return getGson().fromJson(new JSONObject(json).getJSONObject("data").toString(), clazz);
    }

    public static <T> T parseRootJson2Bean(String json, Class<T> clazz, String obj_key) throws JSONException {
        return getGson().fromJson(new JSONObject(json).getJSONObject("data").getJSONObject(obj_key).toString(), clazz);
    }

    public static <T> T parseJson2Bean(String json, Type type) {
        return getGson().fromJson(json, type);
    }

    public static String getHint(String rootJson) {
        try {
            return new JSONObject(rootJson).getString("hint");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static <W> String parseMap2String(Map<String, W> map) {
        String jsonStr = getGson().toJson(map);
        return jsonStr;
    }

    public static <T> T parseMap2Bean(Map<String, ?> map, Class<T> clazz) {
        return getGson().fromJson(getGson().toJson(map), clazz);
    }

    /**
     * 用来兼容Android4.4以下版本的remove方法
     *
     * @param jsonArray
     * @param index
     * @return
     */
    public static JSONArray removeCompatibilityKITKAT(JSONArray jsonArray, int index) {
        JSONArray mJsonArray = new JSONArray();
        if (index < 0)
            return mJsonArray;
        if (index > jsonArray.length())
            return mJsonArray;
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                if (i != index) {
                    mJsonArray.put(jsonArray.getJSONObject(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mJsonArray;
    }
}
