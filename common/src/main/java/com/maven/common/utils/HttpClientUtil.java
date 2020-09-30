package com.maven.common.utils;

import cn.hutool.http.HttpUtil;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    //post
    public static String Post(String url, Map<String, List<String>> requestMap) throws IOException {
        //获取checkId Post
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        List<String> domainList = requestMap.get("domains");
        List<String> tldsList = requestMap.get("tlds");
        //添加form集合
        for (int i = 0; i < domainList.size(); i++) {
            builder.add("domains[]", domainList.get(i)).build();
        }
        for (int i = 0; i < tldsList.size(); i++) {
            builder.add("tlds[]", tldsList.get(i)).build();
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        String responseStr = response.body().string();
        return responseStr;
    }


    //get
    public static String get(String url ) {
        String resp = HttpUtil.get(url);
      return resp;
    }
}
