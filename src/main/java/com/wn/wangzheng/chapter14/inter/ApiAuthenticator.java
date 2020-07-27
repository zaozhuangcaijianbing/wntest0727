package com.wn.wangzheng.chapter14.inter;

import com.wn.wangzheng.chapter14.ApiRequest;

public interface ApiAuthenticator {
    void auth(String url);
    void auth(ApiRequest apiRequest);
}
