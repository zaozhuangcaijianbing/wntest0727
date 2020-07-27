package com.wn.wangzheng.chapter14.client;

import com.wn.wangzheng.chapter14.ApiAuthenticatorImpl;
import com.wn.wangzheng.chapter14.MySqlCredentialStorage;
import com.wn.wangzheng.chapter14.inter.ApiAuthenticator;
import com.wn.wangzheng.chapter14.inter.CredentialStorage;

public class Client {
    public static void main(String[] args) {
        String url = "http://www.xzg.com/user?appId=abc&ts=1589956848000&token=xyz";
        CredentialStorage credentialStorage = new MySqlCredentialStorage();
        ApiAuthenticator authenticator = new ApiAuthenticatorImpl(credentialStorage);
        authenticator.auth(url);
    }
}
