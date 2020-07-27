package com.wn.wangzheng.chain.demo1;

public class HandlerA extends Handler {
    @Override
    public void handle() {
        boolean handled = false;
        //...
        if (!handled && successor != null) {
            successor.handle();
        }
    }
}
