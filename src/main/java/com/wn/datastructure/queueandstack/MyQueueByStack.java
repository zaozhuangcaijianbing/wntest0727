package com.wn.datastructure.queueandstack;

import java.util.Stack;

//6、使用栈实现队列**** 可以使用两个栈
public class MyQueueByStack {
    private Stack<Integer> a;// 输入栈
    private Stack<Integer> b;// 输出栈

    public MyQueueByStack() {
        a = new Stack<>();
        b = new Stack<>();
    }

    public void push(int x) {
        a.push(x);
    }

    public int pop() {
        // 如果b栈为空，则将a栈全部弹出并压入b栈中，然后b.pop()
        if (b.isEmpty()) {
            while (!a.isEmpty()) {
                b.push(a.pop());
            }
        }
        return b.pop();
    }

    public int peek() {
        if (b.isEmpty()) {
            while (!a.isEmpty()) {
                b.push(a.pop());
            }
        }
        return b.peek();
    }

    public boolean empty() {
        return a.isEmpty() && b.isEmpty();
    }


    public static void main(String[] args) {
        MyQueueByStack obj = new MyQueueByStack();
        obj.push(1);
        obj.push(2);
        int param_2 = obj.pop();
        int param_3 = obj.peek();
        obj.push(3);
        boolean param_4 = obj.empty();
        int peek = obj.peek();
    }
}
