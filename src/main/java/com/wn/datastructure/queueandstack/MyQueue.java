package com.wn.datastructure.queueandstack;

import java.util.ArrayList;
import java.util.List;

//1队列简单数组实现，
// 使用动态数组和指向队列头部的索引
//上面的实现很简单，但在某些情况下效率很低。 随着起始指针的移动，浪费了越来越多的空间。
public class MyQueue {
    // store elements
    private List<Integer> data;
    // a pointer to indicate the start position
    private int p_start;

    public MyQueue() {
        data = new ArrayList<Integer>();
        p_start = 0;
    }

    /**
     * Insert an element into the queue. Return true if the operation is successful.
     */
    public boolean enQueue(int x) {
        data.add(x);
        return true;
    }

    ;

    /**
     * Delete an element from the queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (isEmpty() == true) {
            return false;
        }
        p_start++;
        return true;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        return data.get(p_start);
    }

    /**
     * Checks whether the queue is empty or not.
     */
    public boolean isEmpty() {
        return p_start >= data.size();
    }


    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.enQueue(5);
        q.enQueue(3);
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
        q.deQueue();
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
        q.deQueue();
        if (q.isEmpty() == false) {
            System.out.println(q.Front());
        }
    }
}
