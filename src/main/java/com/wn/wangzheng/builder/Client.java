package com.wn.wangzheng.builder;

public class Client {
    public static void main(String[] args) {
        ResourcePoolConfig config = new ResourcePoolConfig.
                Builder().setName("dbconnectionpool").setMaxTotal(16).setMaxIdle(10).setMinIdle(12).build();
    }
}
