package com.wn.wangzheng.chapter14;

import com.wn.wangzheng.chapter14.utils.UrlUtil;

public class ApiRequest {
    private String baseUrl;
    private String appId;
    private long timestamp;
    private String token;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ApiRequest(String baseUrl, String appId, long timestamp, String token) {
        this.baseUrl = baseUrl;
        this.appId = appId;
        this.timestamp = timestamp;
        this.token = token;
    }

    //解析url生成ApiRequest
    //比如http://www.xzg.com/user?id=123&appid=abc&pwd=def123&ts=1589954298&token=xyz
    public static ApiRequest buildFromUrl(String fullUrl) {
        UrlUtil.UrlEntity urlEntity = UrlUtil.parse(fullUrl);
        String baseUrl = urlEntity.baseUrl;
        String appId = urlEntity.getParam("appId");
        String token = urlEntity.getParam("token");
        long timestamp = Long.valueOf(urlEntity.getParam("ts"));
        ApiRequest apiRequest = new ApiRequest(baseUrl,appId,timestamp,token);
        return apiRequest;
    }

}
