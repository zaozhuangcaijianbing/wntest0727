package com.wn.datastructure.queueandstack;

import java.util.LinkedList;
import java.util.Queue;

//6、使用队列实现栈**** 可是使用两个队列
public class MyStackByQueue {
    public Queue<Integer> stack;

    /**
     * Initialize your data structure here.
     */
    public MyStackByQueue() {
        stack = new LinkedList<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        stack.offer(x);
        for (int i = 0; i < stack.size() - 1; i++) {
            Integer remove = stack.remove();
            stack.offer(remove);
        }
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return stack.remove();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return stack.peek();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return stack.size() == 0;
    }
}
