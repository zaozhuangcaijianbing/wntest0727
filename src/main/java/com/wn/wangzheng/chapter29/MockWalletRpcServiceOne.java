package com.wn.wangzheng.chapter29;

public class MockWalletRpcServiceOne extends WalletRpcService {
    public String moveMoney(Long id, Long fromUserId, Long toUserId, Double amount) {
        return "123bac";
    }
}
