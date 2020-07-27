package com.wn.wangzheng.chapter14;

import com.wn.wangzheng.chapter14.inter.CredentialStorage;

public class MySqlCredentialStorage implements CredentialStorage {

    @Override
    public String getPasswordByAppID(String appId) {
        return "def123";
    }
}
