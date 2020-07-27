package com.wn.wangzheng.chapter14;

import com.wn.wangzheng.chapter14.inter.ApiAuthenticator;
import com.wn.wangzheng.chapter14.inter.CredentialStorage;

public class ApiAuthenticatorImpl implements ApiAuthenticator {
    private CredentialStorage storage;

    public ApiAuthenticatorImpl() {
        storage = new MySqlCredentialStorage();
    }

    public ApiAuthenticatorImpl(CredentialStorage credentialStorage) {
        this.storage = credentialStorage;
    }

    @Override
    public void auth(String url) {
        ApiRequest apiRequest = ApiRequest.buildFromUrl(url);
        auth(apiRequest);
    }

    @Override
    public void auth(ApiRequest apiRequest) {
        AuthToken clientToken = new AuthToken(apiRequest.getToken(),apiRequest.getTimestamp());
        if (clientToken.isExpired()) {
            throw new RuntimeException("Request has expired!");
        }
        final String password = storage.getPasswordByAppID(apiRequest.getAppId());
        AuthToken serverToken = AuthToken.generate(apiRequest.getBaseUrl(),apiRequest.getAppId(),password,apiRequest.getTimestamp());

        if (!clientToken.match(serverToken)) {
            throw new RuntimeException("Client token does not match!");
        }
        System.out.println("pass auth!");
    }
}
