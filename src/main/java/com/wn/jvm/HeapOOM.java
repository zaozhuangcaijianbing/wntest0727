package com.wn.jvm;

import java.util.ArrayList;
import java.util.List;


/**
 * <<深入理解java虚拟机>>第2章
 * VM Args：-Xms10m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new OOMObject());
        }
    }
}

