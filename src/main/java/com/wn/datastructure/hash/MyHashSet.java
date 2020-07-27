package com.wn.datastructure.hash;

//1、不使用任何内建的哈希表库设计一个哈希集合 ***
public class MyHashSet {

    private static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data=data;
            next = null;
        }
    }
    private Node[] elementArray = new Node[10000];
    /** Initialize your data structure here. */
    public MyHashSet() {

    }

    public void add(int key) {
        int bucket = hash(key);
        Node head = elementArray[bucket];
        if(head == null){
            elementArray[bucket] = new Node(key);
        }else{
            Node current = new Node(0);
            current.next=head;
            while(current.next!=null){
                if(current.next.data == key){
                    break;
                }
                current = current.next;
            }
            current.next = new Node(key);

        }

    }

    public void remove(int key) {
        int bucket = hash(key);
        Node head = elementArray[bucket];
        if(head == null)
            return;

        Node newHead = new Node(0);
        Node current = newHead;
        current.next=head;
        while(current != null && current.next!=null){
            if(current.next.data == key){
                current.next = current.next.next;
            }
            current = current.next;
        }
        elementArray[bucket] = newHead.next;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int bucket = hash(key);
        Node head = elementArray[bucket];

        Node current = head;
        while(current!=null){
            if(current.data == key){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private int hash(int key){
        int bucket = key % 10000;
        return bucket;
    }

    public static void main(String[] args) {
        MyHashSet set = new MyHashSet();
        set.add(1);
//        set.add(10001);
//        set.add(20001);
//        set.remove(10001);
        set.remove(1);
        set.add(2);
        set.add(3);
        set.add(3);
        set.add(1);
        set.remove(3);
        set.remove(19);
        set.add(14);
        set.remove(19);
        set.remove(9);
        set.add(0);
        set.add(3);
        set.add(4);
        set.add(0);
        set.remove(9);
//        System.out.println(set.contains(2));
    }
}
