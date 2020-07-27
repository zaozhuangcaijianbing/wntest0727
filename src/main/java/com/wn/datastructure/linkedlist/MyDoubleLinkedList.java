package com.wn.datastructure.linkedlist;

//双向链表
public class MyDoubleLinkedList {
    private Node head;

    private int size;

    /**
     * Initialize your data structure here.
     */
    public MyDoubleLinkedList() {

    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        return 0;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {


    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {

    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {

    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
    }


    private static class Node {
        private int data;
        private Node prev;
        private Node next;

        public Node(int data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        MyDoubleLinkedList list = new MyDoubleLinkedList();
        list.addAtIndex(0, 1);
        list.addAtIndex(1, 2);
//        System.out.println(list.get(0));
//        list.addAtTail(1);
//        list.addAtTail(2);
//        list.deleteAtIndex(2);

        System.out.println(list);
    }
}
