package com.xjtlu.monitor.service;

import com.google.gson.Gson;
import com.xjtlu.monitor.pojo.BaseResponse;
import com.xjtlu.monitor.pojo.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;

public class RequestService {

    public static String hostname = "localhost";
    public static int port = 7890;
    public static Proxy proxy = new Proxy(Proxy.Type.SOCKS,
            new InetSocketAddress(hostname, port));

    public static OkHttpClient client = new OkHttpClient.Builder()
            .proxy(proxy)
            .build();

    // 首先获取参数
    public HashMap<String, String> getParams(String address, Chain chain) {
        HashMap<String, String> params = new HashMap<>();
        params.put("module", "account");
        params.put("action", "txlist");
        params.put("address", address);
        params.put("startblock", "0");
        params.put("endblock", "99999999");
        params.put("sort", "desc");
        params.put("apikey",chain.getApiToken());
        return params;
    }

    // 构建url
    public String buildUrl(String address, Chain chain) {
        HashMap<String, String> params = getParams(address, chain);
        String url = chain.getUrl();
        StringBuilder urlBuilder = new StringBuilder(url);
        urlBuilder.append("?");
        for (String key : params.keySet()) {
            // 第一个不加&
            if (urlBuilder.length() > url.length() + 1) {
                urlBuilder.append("&");
            }
            urlBuilder.append(key).append("=").append(params.get(key));
        }
        url = urlBuilder.toString();
        return url;
    }

    // 通过url发送请求获取响应
    public Request getRequest(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return request;
    }

    // 获取响应，将响应实例化为BaseResponse对象
    public BaseResponse getBaseResponse(String url) throws IOException {
        Request request = getRequest(url);
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            assert response.body() != null;
            Gson gson = new Gson();
            assert response.body() != null;
            Reader reader = response.body().charStream();
            return gson.fromJson(reader, BaseResponse.class);
        }
    }
}
