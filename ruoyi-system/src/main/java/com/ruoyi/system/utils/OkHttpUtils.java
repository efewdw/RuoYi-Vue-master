package com.ruoyi.system.utils;

import okhttp3.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * OkHttp 工具类
 */
@Component
public class OkHttpUtils {
    // 单例 OkHttpClient（复用连接池，避免频繁创建销毁）
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS) // 连接超时
            .readTimeout(10, TimeUnit.SECONDS)    // 读取超时
            .writeTimeout(10, TimeUnit.SECONDS)   // 写入超时
            .build();

    /**
     * GET 请求
     * @param url 接口地址
     * @param headers 请求头（可选）
     * @return 接口返回结果
     */
    public static String doGet(String url, Map<String, String> headers) {
        // 构建请求头
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(requestBuilder::addHeader);
        }

        Request request = requestBuilder.build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            // 验证响应状态
            if (!response.isSuccessful()) {
                throw new IOException("第三方接口请求失败，状态码：" + response.code());
            }
            ResponseBody body = response.body();
            return body != null ? body.string() : null;
        } catch (IOException e) {
            throw new RuntimeException("GET 请求第三方接口异常", e);
        }
    }

    /**
     * POST JSON 请求（最常用，传递 JSON 字符串）
     * @param url 接口地址
     * @param jsonParam JSON 参数
     * @param headers 请求头（可选）
     * @return 接口返回结果
     */
    public static String doPostJson(String url, String jsonParam, Map<String, String> headers) {
        // 构建 JSON 请求体
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                StringUtils.hasText(jsonParam) ? jsonParam : "{}"
        );

        // 构建请求
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(requestBody);
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(requestBuilder::addHeader);
        }

        Request request = requestBuilder.build();
        try (Response response = OK_HTTP_CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("第三方接口请求失败，状态码：" + response.code());
            }
            ResponseBody body = response.body();
            return body != null ? body.string() : null;
        } catch (IOException e) {
            throw new RuntimeException("POST JSON 请求第三方接口异常", e);
        }
    }
}