package com.wn.wangzheng.chapter29;

public class TransactionLock {
    public boolean lock(String id) {
        return RedisDistributedLock.getSingletonIntance().lockTransction(id);
    }

    public void unlock() {
        RedisDistributedLock.getSingletonIntance().unlockTransction(id);
    }
}
