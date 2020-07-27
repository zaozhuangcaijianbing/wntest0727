package com.wn.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * <<深入理解java虚拟机>>第2章
 * VM Args：-Xmx20M -XX:MaxDirectMemorySize=10M
 * 本地直接内存溢出
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
