package com.wn.datastructure.lru;

public class Node {
    public int key;
    public int val;
    public Node prev;
    public Node next;

    public Node(int k, int v) {
        this.key = k;
        this.val = v;
    }
}

