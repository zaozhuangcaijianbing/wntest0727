package com.wn.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {
    public static void main(String[] args) throws Exception{
        Variable.add();
        Thread.sleep(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Variable.add();
            }
        }).start();
    }
}

class Variable {
    public static ReadWriteLock o = new ReentrantReadWriteLock();
    private static Integer count = 0;

    public static Integer add() {
        try {
            Lock lock = o.readLock();
            lock.lock();
            return ++count;
        } finally {
            o.readLock().unlock();
        }
    }

    public static Integer subtract() {
        try {
            Lock lock = o.readLock();
            lock.lock();
            return --count;
        } finally {
            o.readLock().unlock();
        }
    }


}
