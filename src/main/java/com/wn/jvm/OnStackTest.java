package com.wn.jvm;

/**
 * 栈上分配
 * -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-UseTLAB  -XX:+EliminateAllocations
 *
 * -XX:+DoEscapeAnalysis启用逃逸分析，默认就是开启
 * -XX:+EliminateAllocations 开启标量替换，允许将对象打散分配在栈上，默认就是开启
 * -XX:-UseTLAB 关闭了tlab，默认开启
 *
 */
public class OnStackTest {
    public static class User {
        public int id = 0;
        public String name = "";
    }

    public static void alloc() {
        User u = new User();
        u.id = 5;
        u.name = "geym";
    }

    public static void main(String[] args) throws InterruptedException {
        long b = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long e = System.currentTimeMillis();
        System.out.println(e - b);
    }
}
