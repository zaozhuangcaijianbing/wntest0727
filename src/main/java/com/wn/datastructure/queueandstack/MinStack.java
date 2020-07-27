package com.wn.datastructure.queueandstack;

import java.util.Stack;

//3、设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。***
public class MinStack {

    private Stack<Integer> stack = new Stack<Integer>();

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(x);
            stack.push(x);
        } else {
            int tmp = stack.peek();
            stack.push(x);
            if (tmp < x) {
                stack.push(tmp);
            } else {
                stack.push(x);
            }
        }
    }

    public void pop() {
        stack.pop();
        stack.pop();
    }

    public int top() {
        return stack.get(stack.size() - 2);
    }

    public int getMin() {
        return stack.peek();
    }
}
